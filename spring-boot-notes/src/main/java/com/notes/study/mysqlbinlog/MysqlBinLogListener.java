package com.notes.study.mysqlbinlog;


import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.github.shyiko.mysql.binlog.event.EventType.*;

/**
 * @author jidn
 * @Date 2020/10/21
 */
public class MysqlBinLogListener  extends AbstractMain implements BinaryLogClient.EventListener{

    @Option(name = "-consume_threads", usage = "the thread num of consumer")
    private int consumerThreads = 3;

    private BinaryLogClient parseClient;

    private BlockingQueue<LogItem> queue;
    private final ExecutorService consumer;

    // 存放每张数据表对应的listener
    private Multimap<String, BinLogListener> listeners;

    private Conf conf;
    private Map<String, Map<String, Colum>> dbTableCols;
    private String dbTable;


    public MysqlBinLogListener(Conf conf) {
        BinaryLogClient client = new BinaryLogClient(conf.host, conf.port, conf.username, conf.passwd);
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        client.setEventDeserializer(eventDeserializer);
        this.parseClient = client;
        this.queue = new ArrayBlockingQueue<>(1024);
        this.conf = conf;
        listeners = ArrayListMultimap.create();
        dbTableCols = new ConcurrentHashMap<>();
        this.consumer = Executors.newFixedThreadPool(consumerThreads);
    }


    @Override
    public void run()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + conf.host + ":" + conf.port, conf.username, conf.passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Map<String, Colum> cols = getColMap(connection, "video", "student");
            System.out.println(cols);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onEvent(Event event) {
        EventType eventType = event.getHeader().getEventType();

        if (eventType == EventType.TABLE_MAP) {
            TableMapEventData tableData = event.getData();
            String db = tableData.getDatabase();
            String table = tableData.getTable();
            dbTable = getdbTable(db, table);
        }



        // 只处理添加删除更新三种操作
        if (isWrite(eventType) || isUpdate(eventType) || isDelete(eventType)) {
            if (isWrite(eventType)) {
                WriteRowsEventData data = event.getData();
                for (Serializable[] row : data.getRows()) {
                    if (dbTableCols.containsKey(dbTable)) {
                        LogItem e = LogItem.itemFromInsert(row, dbTableCols.get(dbTable));
                        e.setDbTable(dbTable);
                        e.setEventName(eventType.name());
                        queue.add(e);
                    }
                }
            }
            if(isDelete(eventType)){
                DeleteRowsEventData data = event.getData();
                for (Serializable[] row : data.getRows()) {
                    if (dbTableCols.containsKey(dbTable)) {
                        LogItem e = LogItem.itemFromInsert(row, dbTableCols.get(dbTable));
                        e.setDbTable(dbTable);
                        e.setEventName(eventType.name());
                        queue.add(e);
                    }
                }
            }
            if(isUpdate(eventType)){
                UpdateRowsEventData data = event.getData();
                List<Map.Entry<Serializable[], Serializable[]>> rows = data.getRows();
                System.out.println(data);
                System.out.println(data.getRows().get(0).getValue()[0]);
                System.out.println(data.getRows().get(0).getValue()[1]);
                System.out.println(data.getRows().get(0).getValue()[2]);
                for (Map.Entry<Serializable[], Serializable[]>  row : data.getRows()) {
                    if (dbTableCols.containsKey(dbTable)) {
                       LogItem e = LogItem.itemFromUpdate(row, dbTableCols.get(dbTable));
                        e.setDbTable(dbTable);
                        e.setEventName(eventType.name());
                        queue.add(e);
                    }
                }
            }
        }
    }


    public void regListener(String db, String table, BinLogListener listener) throws Exception {
        String dbTable = getdbTable(db, table);
        Class.forName("com.mysql.jdbc.Driver");
        // 保存当前注册的表的colum信息
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + conf.host + ":" + conf.port, conf.username, conf.passwd);
        Map<String, Colum> cols = getColMap(connection, db, table);
        dbTableCols.put(dbTable, cols);

        // 保存当前注册的listener
        listeners.put(dbTable, listener);
    }

    private static String getdbTable(String db, String table) {
        return db + "_" + table;
    }

    public static Map<String, Colum> getColMap(Connection connection, String db, String table) {
        try {
            PreparedStatement ps
                    = connection.prepareStatement("SELECT TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, DATA_TYPE, ORDINAL_POSITION " +
                    "FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? and TABLE_NAME = ?");
            ps.setString(1, db);
            ps.setString(2, table);
            ResultSet rs = ps.executeQuery();
            Map<String, Colum> map = new HashMap<>(rs.getRow());
            while (rs.next()) {
                String schema = rs.getString("TABLE_SCHEMA");
                String tableName = rs.getString("TABLE_NAME");
                String column = rs.getString("COLUMN_NAME");
                int idx = rs.getInt("ORDINAL_POSITION");
                String dataType = rs.getString("DATA_TYPE");
                if (column != null && idx >= 1) {
                    map.put(column, new Colum(schema, tableName, idx - 1, column, dataType)); // sql的位置从1开始
                }
            }
            ps.close();
            rs.close();
            return map;
        } catch (SQLException e) {
            logger.error("load db conf error, db_table={}:{} ", db, table, e);
        }
        return null;
    }

    public static class Colum {
        public final String colName; // 列名
        public int inx;
        public final String dataType; // 类型

        public Colum(String schema, String table, int idx, String colName, String dataType) {
            this.colName = colName;
            this.dataType = dataType;
            this.inx = idx;
        }
    }

    public void parse() throws IOException {
        parseClient.registerEventListener(this);

        for (int i = 0; i < consumerThreads; i++) {
            consumer.submit(() -> {
                while (true) {
                    if (queue.size() > 0) {
                        try {
                            LogItem item = queue.take();
                            String dbtable = item.getDbTable();
                            listeners.get(dbtable).forEach(l -> {
                                l.onEvent(item);
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Thread.sleep(1000);
                }
            });
        }
        parseClient.connect();
    }

    public static class Conf {
        String host;
        int port;
        String username;
        String passwd;
    }


    public static void main(String[] args) throws Exception {
        Conf conf = new Conf();
        conf.host = "127.0.0.1";
        conf.port = 3306;
        conf.username = conf.passwd = "root";

        MysqlBinLogListener mysqlBinLogListener = new MysqlBinLogListener(conf);
        mysqlBinLogListener.parseArgsAndRun(args);
        mysqlBinLogListener.regListener("video", "student", item -> {

            System.out.println(new String((byte[]) item.getAfter().get("name")));
            System.out.println(new String((byte[]) item.getAfter().get("address")));
            logger.info("EventType={}  table={}, value = {}  before={}", item.getEventName(),item.getDbTable(), item.getAfter(), item.getBefore());
        });

        mysqlBinLogListener.parse();
    }

}

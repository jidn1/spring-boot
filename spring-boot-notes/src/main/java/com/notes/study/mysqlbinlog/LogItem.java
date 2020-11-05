package com.notes.study.mysqlbinlog;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jidn
 * @Date 2020/10/21
 */
public class LogItem {


    private static final long serialVersionUID = 5503152746318421290L;

    private String dbTable;
    private int eventType;
    private String eventName;
    private Long timestamp = null;
    private Long serverId = null;
    // 存储字段-之前的值之后的值
    private Map<String, Serializable> before = null;
    private Map<String, Serializable> after = null;
    // 字段--类型
    private Map<String, MysqlBinLogListener.Colum> colums = null;

    public static LogItem itemFromInsert(Serializable[] row, Map<String, MysqlBinLogListener.Colum> columMap) {
        if (null == row || null == columMap) {
            return null;
        }
        if (row.length != columMap.size()) {
            return null;
        }
        LogItem i = new LogItem();
        i.colums = columMap;
        i.before = null;

        Map<String, Serializable> af = new HashMap<>();
        columMap.entrySet().forEach(entry -> {
            String key = entry.getKey();
            MysqlBinLogListener.Colum colum = entry.getValue();
            af.put(key, row[colum.inx]);
        });

        i.after = af;
        return i;

    }


    public static LogItem itemFromUpdate(Map.Entry<Serializable[], Serializable[]> row, Map<String, MysqlBinLogListener.Colum> columMap) {
        if (null == row || null == columMap) {
            return null;
        }

        LogItem i = new LogItem();
        i.colums = columMap;
        i.before = null;

        Map<String, Serializable> af = new HashMap<>();
        columMap.entrySet().forEach(entry -> {
            String key = entry.getKey();
            MysqlBinLogListener.Colum colum = entry.getValue();
            String v = (String) row.getValue()[colum.inx];
            af.put(key, v);
        });

        i.after = af;
        return i;

    }




    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventType() {
        return eventType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Long getServerId() {
        return serverId;
    }

    public Map<String, Serializable> getBefore() {
        return before;
    }

    public Map<String, Serializable> getAfter() {
        return after;
    }

    public Map<String, MysqlBinLogListener.Colum> getColums() {
        return colums;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public void setBefore(Map<String, Serializable> before) {
        this.before = before;
    }

    public void setAfter(Map<String, Serializable> after) {
        this.after = after;
    }

    public void setColums(Map<String, MysqlBinLogListener.Colum> colums) {
        this.colums = colums;
    }

    public String getDbTable() {
        return dbTable;
    }

    public void setDbTable(String dbTable) {
        this.dbTable = dbTable;
    }

}

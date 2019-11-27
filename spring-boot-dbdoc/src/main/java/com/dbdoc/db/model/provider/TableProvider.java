package com.dbdoc.db.model.provider;

import com.dbdoc.db.model.Table;
import com.dbdoc.utils.PropertiesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/27 12:41
 * @Description: 数据库表模型提供类
 */
public class TableProvider extends DatabaseProvider {

    private static TableProvider instance = null;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public synchronized static TableProvider getInstance() {
        if (instance == null) {
            instance = new TableProvider();
        }
        return instance;
    }

    /***
     * 获取数据库中所有的表
     * @return
     * @throws SQLException
     */
    public List getAllTables() throws SQLException {
        DatabaseMetaData dbMetaData = super.getMetaData();
        ResultSet rs = dbMetaData.getTables(PropertiesProvider.getNullIfBlank("jdbc_catalog"), PropertiesProvider.getNullIfBlank("jdbc_schema"), null, null);
        List<Table> tables = new ArrayList<Table>();
        log.info("<<<<<<<<<<<<<<<<<<<<<获取所有表开始>>>>>>>>>>>>>>>>>");
        int i = 0;
        while (rs.next()) {
            Table table = createTableModel(connection, rs);
            table.setIndex(++i);
            if (null != table) {
                tables.add(table);
            }


        }
        log.info("<<<<<<<<<<<<<<<<<<<<<获取所有表结束>>>>>>>>>>>>>>>>>");
        return tables;
    }

    public Table createTableModel(Connection conn, ResultSet rs) throws SQLException {
        String realTableName = null;
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            String schemaName = rs.getString("TABLE_SCHEM") == null ? "" : rs.getString("TABLE_SCHEM");
            realTableName = rs.getString("TABLE_NAME");
            String comment = getCommentByTableName(realTableName);
            String tableType = rs.getString("TABLE_TYPE");
            String remarks = rs.getString("REMARKS");

            if (remarks == null && isOracleDataBase()) {
                remarks = getOracleTableComments(realTableName);
            }
            Table table = new Table();
            table.setName(realTableName);
            table.setType(tableType);
            table.setRemarks(comment);

            if ("VIEW".equals(tableType)) {
                log.info("去除视图......");
                return null;
            }

            /**sql server**/
            if ("SYSTEM_TABLE".equals(tableType)) {
                log.info("去除系统表......");
                return null;
            }

            /**Oracle****/
            if ("SYNONYM".equals(tableType) && isOracleDataBase()) {
                table.setOwnerSynonymName(getSynonymOwner(realTableName));
            }

            System.out.println(realTableName);
            System.out.println(comment);
            if ("BIN".equals(realTableName.substring(0, 3)) && isOracleDataBase()) {
                log.info("去除orcal recyclebin中的表(10g)......");
                return null;
            }
            ColumnProvider.getInstance().createTableColumnsModel(table);
            //获取列信息后,将是主键的列初始化处理
            table.initPrimaryKeyColumns();
//			table.initExportedKeys(conn.getMetaData());
//			table.initImportedKeys(conn.getMetaData());
            return table;
        } catch (SQLException e) {
            throw new RuntimeException("error：创建表:" + realTableName + "出现异常.\n", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

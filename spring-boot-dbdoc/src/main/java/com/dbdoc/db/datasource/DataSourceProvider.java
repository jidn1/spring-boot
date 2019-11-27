package com.dbdoc.db.datasource;

import com.dbdoc.service.Constants;
import com.dbdoc.utils.PropertiesProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/27 12:36
 * @Description: 读取数据源类
 */
public class DataSourceProvider {

    private static Connection connection;
    private static DataSource dataSource;

    public synchronized static Connection getNewConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = getDataSource().getConnection();
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setDataSource(DataSource dataSource) {
        DataSourceProvider.dataSource = dataSource;
    }

    public synchronized static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new DriverManagerDataSource(PropertiesProvider.getRequiredProperty(Constants.JDBC_URL),
                    PropertiesProvider.getRequiredProperty(Constants.JDBC_USERNAME),
                    PropertiesProvider.getRequiredProperty(Constants.JDBC_PASSWORD),
                    PropertiesProvider.getRequiredProperty(Constants.JDBC_DRIVER));
        }
        return dataSource;
    }
}

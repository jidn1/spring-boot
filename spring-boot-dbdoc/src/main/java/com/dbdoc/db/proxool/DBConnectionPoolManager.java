package com.dbdoc.db.proxool;

import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/27 12:45
 * @Description:
 */
public class DBConnectionPoolManager {

    private static Logger log = Logger.getAnonymousLogger();
    private static DBConnectionPoolManager dbcpm = null;
    private Connection con = null;

    /**
     * 构造函数
     *
     * @return DBConnectionPoolManager
     */
    private DBConnectionPoolManager() {
        log.info(getClass().getResource("/").getPath());
        InputStream is = getClass().getResourceAsStream("/jdbc_proxool.xml");
        try {
            JAXPConfigurator.configure(new InputStreamReader(is), false);
            log.info("<<<<<<proxool.xml配置文件加载成功!>>>>>>");
        } catch (Exception e) {
            log.info("<<<<<<proxool.xml配置文件加载失败!>>>>>>");
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * 获取实例 单态
     * @return
     */
    public static synchronized DBConnectionPoolManager getInstance(){
        if(null==dbcpm)
        {dbcpm =new DBConnectionPoolManager();}
        return dbcpm;
    }

    /***
     * ��ȡ����
     * @return
     */
    public Connection getConnection(){
        try{
            con = DriverManager.getConnection("proxool.gxgljsyh");
        }catch(Exception e){
            log.info("-<<<<<<数据库链接失败,请检查proxool.xml文件配置是否正确!>>>>>>");
            log.info(e.getMessage());
        }
        return con;
    }

    public static void main(String[] args){
        try{
            if (DBConnectionPoolManager.getInstance().getConnection()!=null){
                log.info("- <<<<<<链接成功！>>>>>>");
            }else{
                log.info("-<<<<<<链接失败！>>>>>>");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}

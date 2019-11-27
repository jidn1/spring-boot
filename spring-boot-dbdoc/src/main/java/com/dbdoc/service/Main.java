package com.dbdoc.service;

import com.dbdoc.db.model.provider.TableProvider;
import com.dbdoc.utils.FreemarkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/27 13:07
 * @Description:
 */
public class Main {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String template_file = "static/template/simple.xml";
    public static final String out_dir = "D:\\ifeng\\数据库说明文档.doc";

    public static void main(String args[]) throws IOException {
        Map propMap = new HashMap();
        try {
            List tables = TableProvider.getInstance().getAllTables();
            propMap.put("tableList", tables);
            FreemarkerUtils.writeTemplateToFile(Main.template_file, propMap,
                    Main.out_dir);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("生成完毕!!!");
        // 打开生成的文件
       // Runtime.getRuntime().exec("cmd.exe /c start " + out_dir);
    }
}

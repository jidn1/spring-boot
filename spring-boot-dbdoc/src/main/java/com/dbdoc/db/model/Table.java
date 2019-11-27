package com.dbdoc.db.model;

import lombok.Data;

import java.util.*;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/27 12:39
 * @Description:
 */
@Data
public class Table {

    private int index;
    /**
     * 表名
     */
    private String name;
    /**
     * 注释
     */
    private String remarks;
    /**
     * 类名
     */
    private String className;
    /**
     * 表类型
     */
    private String type;

    private String desc;

    private String txt;

    private Set<Column> columns = new LinkedHashSet();

    private List<Column> primaryKeyColumns = new ArrayList();

    private String ownerSynonymName = null;

    public void initPrimaryKeyColumns() {
        if (this.columns.size() > 0) {
            for (Iterator i = columns.iterator(); i.hasNext();) {
                Column column = (Column) i.next();
                if (column.is_isPk()) {
                    primaryKeyColumns.add(column);
                }
            }
        }
    }

    public void addColumn(Column column) {
        columns.add(column);
    }
}

package com.dbdoc.db.model;

import com.dbdoc.db.utils.DatabaseDataTypesUtils;
import lombok.Data;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/27 12:38
 * @Description:
 */
@Data
public class Column {

    /**
     * 列名称
     */
    private String _sqlName;

    /**
     * 列类型名称
     */
    private String _sqlTypeName;
    /**
     * 列类型输出的字符串
     */
    private String _sqlTypeOuter;
    /**
     * 注释
     */
    private String _remarks;
    /**
     * 默认值
     */
    private String _defaultValue;
    /**
     * 列类型
     */
    private int _sqlType;
    /**
     * 大小
     */
    private int _size;

    private int _decimalDigits;
    /**
     * 是否为主键
     */
    private boolean _isPk;
    /**
     * 是否为外键
     */
    private boolean _isFk;
    /**
     * 是否可以为空
     */
    private boolean _isNullable;
    /**
     * 是否为索引
     */
    private boolean _isIndexed;
    /**
     * 是否Unique
     */
    private boolean _isUnique;


    public Column(int sqlType, String sqlTypeName, String sqlName, int size, int decimalDigits, boolean isPk, boolean isNullable, boolean isIndexed, boolean isUnique, String defaultValue, String remarks) {
        _sqlType = sqlType;
        _sqlName = sqlName;
        _sqlTypeName = sqlTypeName;
        _size = size;
        _decimalDigits = decimalDigits;
        _isPk = isPk;
        _isNullable = isNullable;
        _isIndexed = isIndexed;
        _isUnique = isUnique;
        _defaultValue = defaultValue;
        _remarks = remarks;

    }

    public String get_sqlTypeOuter() {
        if (DatabaseDataTypesUtils.isDate(_sqlTypeName)) {
            return _sqlTypeName;
        }
        return _sqlTypeName + "(" + _size + ")";
    }
}

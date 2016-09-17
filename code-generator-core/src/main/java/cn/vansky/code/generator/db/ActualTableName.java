/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.db;

import cn.vansky.code.generator.util.StringUtility;

/**
 * 实际表名信息
 * Author: CK.
 * Date: 2015/6/6.
 */
public class ActualTableName {
    /** 表名称 */
    private String tableName;
    /** 表类别 */
    private String catalog;
    /** 表模式 */
    private String schema;
    /** 表全名 */
    private String fullName;

    public ActualTableName() {
    }

    public ActualTableName(String catalog, String schema, String tableName) {
        this.catalog = catalog;
        this.schema = schema;
        this.tableName = tableName;
        fullName = StringUtility.composeFullyQualifiedTableName(catalog, schema, tableName, '.');
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ActualTableName)) {
            return false;
        }

        return obj.toString().equals(this.toString());
    }

    public int hashCode() {
        return fullName.hashCode();
    }

    public String toString() {
        return fullName;
    }
}

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.db;

import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.JavaTypeInfoEnum;

import java.sql.Types;

/**
 * 数据库列信息
 * Author: CK.
 * Date: 2015/6/6.
 */
public class ColumnInfo {
    /** 数据库实际列名 */
    protected String actualColumnName;
    /** JDBC类型 */
    protected int jdbcType;
    /** JDBC类型名称 */
    protected String jdbcTypeName;
    /** 是否允许空值 */
    protected boolean nullable;
    /** 长度 */
    protected int length;
    /** 精度 */
    protected int scale;
    /** 是否自增 */
    protected boolean identity;
    /** 是否序列 */
    protected boolean isSequenceColumn;
    /** JAVA属性 */
    protected String javaProperty;
    /** JAVA类型 */
    protected JavaTypeInfo javaTypeInfo;
    /** 表别名 */
    protected String tableAlias;
    /** 自定义类型转换 */
    protected String typeHandler;
    /** 表信息 */
    protected TableInfo tableInfo;
    /** 注释 */
    protected String comment;
    /** 默认值 */
    protected String defaultValue;
    /** 主键名称 */
    protected String pkName;
    /** 主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列） */
    protected Short keySeq;

    public ColumnInfo() {

    }

    public String getJavaProperty(String prefix) {
        if (prefix == null) {
            return javaProperty;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(javaProperty);

        return sb.toString();
    }

    public boolean isJDBCDateColumn() {
        return javaTypeInfo.equals(JavaTypeInfoEnum.DATE.getJavaTypeInfo()) && "DATE".equalsIgnoreCase(jdbcTypeName);
    }

    public boolean isJDBCTimeColumn() {
        return javaTypeInfo.equals(JavaTypeInfoEnum.DATE.getJavaTypeInfo()) && "TIME".equalsIgnoreCase(jdbcTypeName);
    }

    public boolean isJdbcCharacterColumn() {
        return jdbcType == Types.CHAR || jdbcType == Types.CLOB
                || jdbcType == Types.LONGVARCHAR || jdbcType == Types.VARCHAR
                || jdbcType == Types.LONGNVARCHAR || jdbcType == Types.NCHAR
                || jdbcType == Types.NCLOB || jdbcType == Types.NVARCHAR;
    }

    public boolean isStringColumn() {
        return javaTypeInfo.equals(JavaTypeInfoEnum.STRING.getJavaTypeInfo());
    }

    public String getActualColumnName() {
        return actualColumnName;
    }

    public void setActualColumnName(String actualColumnName) {
        this.actualColumnName = actualColumnName;
    }

    public int getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;
    }

    public boolean isSequenceColumn() {
        return isSequenceColumn;
    }

    public void setSequenceColumn(boolean isSequenceColumn) {
        this.isSequenceColumn = isSequenceColumn;
    }

    public String getJavaProperty() {
        return javaProperty;
    }

    public void setJavaProperty(String javaProperty) {
        this.javaProperty = javaProperty;
    }

    public JavaTypeInfo getJavaTypeInfo() {
        return javaTypeInfo;
    }

    public void setJavaTypeInfo(JavaTypeInfo javaTypeInfo) {
        this.javaTypeInfo = javaTypeInfo;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTypeHandler() {
        return typeHandler;
    }

    public void setTypeHandler(String typeHandler) {
        this.typeHandler = typeHandler;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public Short getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(Short keySeq) {
        this.keySeq = keySeq;
    }
}

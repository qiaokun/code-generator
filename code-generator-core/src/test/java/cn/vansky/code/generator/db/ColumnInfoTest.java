/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.db;

import cn.vansky.code.generator.java.JavaTypeInfo;

import java.util.ArrayList;
import java.util.List;

public class ColumnInfoTest {

    static TableInfo tableInfo = TableInfoTest.build();

    public static ColumnInfo buildId() {
        ColumnInfo id = new ColumnInfo();
        id.setActualColumnName("Id");
        id.setJdbcType(4);
        id.setJdbcTypeName("INTEGER");
        id.setLength(10);
        id.setIdentity(true);
        id.setJavaProperty("id");
        id.setComment("ID");
        JavaTypeInfo javaTypeInfo = new JavaTypeInfo(Integer.class.getName());
        id.setJavaTypeInfo(javaTypeInfo);
        id.setTableInfo(tableInfo);
        return id;
    }

    public static ColumnInfo buildName() {
        ColumnInfo name = new ColumnInfo();
        name.setActualColumnName("role_name");
        name.setJdbcType(12);
        name.setJdbcTypeName("VARCHAR");
        name.setLength(255);
        name.setIdentity(true);
        name.setJavaProperty("roleName");
        name.setComment("名称");
        JavaTypeInfo nameTypeInfo = new JavaTypeInfo(String.class.getName());
        name.setJavaTypeInfo(nameTypeInfo);
        name.setTableInfo(tableInfo);
        return name;
    }

    public static List<ColumnInfo> buildColumnInfoList() {
        List<ColumnInfo> listAll = new ArrayList<ColumnInfo>();
        listAll.add(buildId());
        listAll.add(buildName());
        return listAll;
    }
}
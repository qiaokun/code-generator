/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.db;

public class TableInfoTest {

    public static TableInfo build() {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setIntrospectedCatalog("framework");
        tableInfo.setIntrospectedTableName("tb_role");
        tableInfo.setDomainObjectName("Role");
        tableInfo.setDomainObjectSubPackage("role");
        return tableInfo;
    }
}
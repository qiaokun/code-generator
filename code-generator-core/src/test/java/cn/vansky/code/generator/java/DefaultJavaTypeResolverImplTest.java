/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.ColumnInfoTest;
import cn.vansky.code.generator.java.resolver.DefaultJavaTypeResolverImpl;
import cn.vansky.code.generator.java.resolver.JavaTypeResolver;
import org.testng.annotations.Test;

public class DefaultJavaTypeResolverImplTest {

    static JavaTypeResolver javaTypeResolver = new DefaultJavaTypeResolverImpl();
    static ColumnInfo id = ColumnInfoTest.buildId();
    static ColumnInfo name = ColumnInfoTest.buildName();

    public void testCalculateJavaType() throws Exception {
        javaTypeResolver.calculateJavaType(id);
        javaTypeResolver.calculateJavaType(name);
    }

    @Test
    public void testCalculateJdbcTypeName() throws Exception {
        String idStr = javaTypeResolver.calculateJdbcTypeName(id);
        System.out.println(idStr);
        String nameStr = javaTypeResolver.calculateJdbcTypeName(name);
        System.out.println(nameStr);
    }

    public static JavaTypeInfo calculateJavaType(ColumnInfo columnInfo) {
        return javaTypeResolver.calculateJavaType(columnInfo);
    }

    public static String calculateJdbcTypeName(ColumnInfo columnInfo) {
        return javaTypeResolver.calculateJdbcTypeName(columnInfo);
    }
}
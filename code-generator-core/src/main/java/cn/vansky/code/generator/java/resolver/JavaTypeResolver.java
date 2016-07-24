/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.resolver;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.java.JavaTypeInfo;

/**
 * JAVA类型解析接口
 * Author: CK.
 * Date: 2015/6/6.
 */
public interface JavaTypeResolver {

    /**
     * 根据JDBC类型，长度，精度，计算JAVA类型名称
     *
     * @param columnInfo 列名
     * @return JAVA类型信息
     */
    JavaTypeInfo calculateJavaType(ColumnInfo columnInfo);

    /**
     * 根据JDBC类型，长度，精度，计算JDBC类型名称
     *
     * @param columnInfo 列名
     * @return JAVA类型名称
     */
    String calculateJdbcTypeName(ColumnInfo columnInfo);
}

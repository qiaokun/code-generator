/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.resolver;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.java.JavaTypeInfo;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JAVA类型解析类（默认）
 * Author: CK.
 * Date: 2015/6/6.
 */
public class DefaultJavaTypeResolverImpl implements JavaTypeResolver {

    protected Map<Integer, JdbcTypeInformation> typeMap;

    public DefaultJavaTypeResolverImpl() {
        typeMap = new HashMap<Integer, JdbcTypeInformation>();
        typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", new JavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", new JavaTypeInfo(Long.class.getName())));
        typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", new JavaTypeInfo("byte[]")));
        typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", new JavaTypeInfo(Boolean.class.getName())));
        typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", new JavaTypeInfo("byte[]")));
        typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", new JavaTypeInfo(Boolean.class.getName())));
        typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", new JavaTypeInfo(String.class.getName())));
        typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", new JavaTypeInfo(String.class.getName())));
        typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", new JavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", new JavaTypeInfo(Date.class.getName())));
        typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", new JavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", new JavaTypeInfo(Double.class.getName())));
        typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", new JavaTypeInfo(Double.class.getName())));
        typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", new JavaTypeInfo(Integer.class.getName())));
        typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", new JavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR", new JavaTypeInfo(String.class.getName())));
        typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("LONGVARBINARY", new JavaTypeInfo("byte[]")));
        typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", new JavaTypeInfo(String.class.getName())));
        typeMap.put(Types.NCHAR, new JdbcTypeInformation("NCHAR", new JavaTypeInfo(String.class.getName())));
        typeMap.put(Types.NCLOB, new JdbcTypeInformation("NCLOB", new JavaTypeInfo(String.class.getName())));
        typeMap.put(Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR", new JavaTypeInfo(String.class.getName())));
        typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", new JavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", new JavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", new JavaTypeInfo(Float.class.getName())));
        typeMap.put(Types.REF, new JdbcTypeInformation("REF", new JavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", new JavaTypeInfo(Short.class.getName())));
        typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", new JavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", new JavaTypeInfo(Date.class.getName())));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", new JavaTypeInfo(Date.class.getName())));
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new JavaTypeInfo(Byte.class.getName())));
        typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", new JavaTypeInfo("byte[]")));
        typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", new JavaTypeInfo(String.class.getName())));
    }

    public JavaTypeInfo calculateJavaType(ColumnInfo columnInfo) {
        JavaTypeInfo answer;
        JdbcTypeInformation jdbcTypeInformation = typeMap.get(columnInfo.getJdbcType());

        if (jdbcTypeInformation == null) {
            switch (columnInfo.getJdbcType()) {
                case Types.DECIMAL:
                case Types.NUMERIC:
                    if (columnInfo.getScale() > 0 || columnInfo.getLength() > 18) {
                        answer = new JavaTypeInfo(BigDecimal.class.getName());
                    } else if (columnInfo.getLength() > 9) {
                        answer = new JavaTypeInfo(Long.class.getName());
                    } else if (columnInfo.getLength() > 4) {
                        answer = new JavaTypeInfo(Integer.class.getName());
                    } else {
                        answer = new JavaTypeInfo(Short.class.getName());
                    }
                    break;
                default:
                    answer = null;
                    break;
            }
        } else {
            answer = jdbcTypeInformation.getJavaTypeInfo();
        }

        return answer;
    }

    public String calculateJdbcTypeName(ColumnInfo columnInfo) {
        String answer;
        JdbcTypeInformation jdbcTypeInformation = typeMap.get(columnInfo.getJdbcType());

        if (jdbcTypeInformation == null) {
            switch (columnInfo.getJdbcType()) {
                case Types.DECIMAL:
                    answer = "DECIMAL";
                    break;
                case Types.NUMERIC:
                    answer = "NUMERIC";
                    break;
                default:
                    answer = null;
                    break;
            }
        } else {
            answer = jdbcTypeInformation.getJdbcTypeName();
        }

        return answer;
    }

    /**
     * JDBC类型信息
     */
    public static class JdbcTypeInformation {
        /**
         * JDBC类型
         */
        private String jdbcTypeName;

        /**
         * JAVA类型
         */
        private JavaTypeInfo JavaTypeInfo;

        public JdbcTypeInformation(String jdbcTypeName, JavaTypeInfo JavaTypeInfo) {
            this.jdbcTypeName = jdbcTypeName;
            this.JavaTypeInfo = JavaTypeInfo;
        }

        public String getJdbcTypeName() {
            return jdbcTypeName;
        }

        public JavaTypeInfo getJavaTypeInfo() {
            return JavaTypeInfo;
        }
    }
}

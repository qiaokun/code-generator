/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.util;

import java.util.StringTokenizer;

/**
 * String操作
 * Author: CK.
 * Date: 2015/6/6.
 */
public class StringUtility {

    public static boolean stringHasValue(String s) {
        return s != null && s.length() > 0;
    }

    /**
     * 获取表全名
     * @param catalog
     * @param schema 数据库名
     * @param tableName 表名
     * @param separator 分隔符
     * @return 表全名
     */
    public static String composeFullyQualifiedTableName(String catalog, String schema,
                                                        String tableName, char separator) {
        StringBuilder sb = new StringBuilder();

        if (stringHasValue(catalog)) {
            sb.append(catalog);
            sb.append(separator);
        }

        if (stringHasValue(schema)) {
            sb.append(schema);
            sb.append(separator);
        } else {
            if (sb.length() > 0) {
                sb.append(separator);
            }
        }

        sb.append(tableName);

        return sb.toString();
    }

    public static boolean stringContainsSpace(String s) {
        return s != null && s.indexOf(' ') != -1;
    }

    public static String escapeStringForJava(String s) {
        StringTokenizer st = new StringTokenizer(s, "\"", true);
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("\"".equals(token)) {
                sb.append("\\\"");
            } else {
                sb.append(token);
            }
        }

        return sb.toString();
    }

    public static String escapeStringForXml(String s) {
        StringTokenizer st = new StringTokenizer(s, "\"", true);
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("\"".equals(token)) {
                sb.append("&quot;");
            } else {
                sb.append(token);
            }
        }

        return sb.toString();
    }

    public static boolean isTrue(String s) {
        return "true".equalsIgnoreCase(s);
    }

    public static boolean stringContainsSQLWildcard(String s) {
        if (s == null) {
            return false;
        }

        return s.indexOf('%') != -1 || s.indexOf('_') != -1;
    }
}

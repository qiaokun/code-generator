/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.util;

import cn.vansky.code.generator.java.JavaKeywords;
import cn.vansky.code.generator.java.JavaTypeInfo;

import java.util.Set;
import java.util.TreeSet;

/**
 * 输出工具类.
 * Author: CK.
 * Date: 2015/6/6.
 */
public class OutputUtil {

    /** 行分割符 */
    private static final String lineSeparator;

    static {
        String ls = System.getProperty("line.separator");
        if (ls == null) {
            ls = "\n";
        }
        lineSeparator = ls;
    }

    /**
     * 对于XML自增两个空格
     *
     * @param sb 两个空格
     * @param indentLevel 级别
     */
    public static void xmlIndent(StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("  ");
        }
    }

    /**
     * 对于JAVA自增四个空格
     *
     * @param sb 四个空格
     * @param indentLevel 级别
     */
    public static void javaIndent(StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("    ");
        }
    }

    /**
     * 换行
     *
     * @param sb 换行符
     */
    public static void newLine(StringBuilder sb) {
        sb.append(lineSeparator);
    }

    /**
     * 导入Set
     *
     * @param importedTypes 导入类型Set
     * @return 导入Set
     */
    public static Set<String> calculateImports(Set<JavaTypeInfo> importedTypes) {
        StringBuilder sb = new StringBuilder();
        Set<String> importStrings = new TreeSet<String>();
        for (JavaTypeInfo info : importedTypes) {
            for (String importString : info.getImportList()) {
                sb.setLength(0);
                sb.append(JavaKeywords.IMPORT);
                sb.append(importString);
                sb.append(';');
                importStrings.add(sb.toString());
            }
        }

        return importStrings;
    }
}

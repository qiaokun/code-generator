/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

import cn.vansky.code.generator.util.OutputUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * 初始化模块
 * Author: CK
 * Date: 2015/7/8.
 */
public class InitializationBlock {
    /** 是否静态 */
    private boolean isStatic;
    /** 具体内容list */
    private List<String> bodyLines;
    /** 注解list */
    private List<String> javaDocLines;

    public InitializationBlock() {
        this(false);
    }

    public InitializationBlock(boolean isStatic) {
        this.isStatic = isStatic;
        bodyLines = new ArrayList<String>();
        javaDocLines = new ArrayList<String>();
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public List<String> getBodyLines() {
        return bodyLines;
    }

    public void addBodyLine(String line) {
        bodyLines.add(line);
    }

    public void addBodyLines(Collection<String> lines) {
        bodyLines.addAll(lines);
    }

    public List<String> getJavaDocLines() {
        return javaDocLines;
    }

    public void addJavaDocLine(String javaDocLine) {
        javaDocLines.add(javaDocLine);
    }

    public void addJavaDocLines(Collection<String> lines) {
        javaDocLines.addAll(lines);
    }

    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        // 注释
        for (String javaDocLine : getJavaDocLines()) {
            OutputUtil.javaIndent(sb, indentLevel);
            sb.append(javaDocLine);
            OutputUtil.newLine(sb);
        }

        OutputUtil.javaIndent(sb, indentLevel);

        if (isStatic()) {
            sb.append(JavaKeywords.STATIC);
        }

        sb.append('{');
        indentLevel++;

        ListIterator<String> listIter = getBodyLines().listIterator();
        while (listIter.hasNext()) {
            String line = listIter.next();
            if (line.startsWith("}")) { 
                indentLevel--;
            }

            OutputUtil.newLine(sb);
            OutputUtil.javaIndent(sb, indentLevel);
            sb.append(line);

            if ((line.endsWith("{") && !line.startsWith(JavaKeywords.SWITCH)) || line.endsWith(":")) {
                indentLevel++;
            }

            if (line.startsWith(JavaKeywords.BREAK)) {
                // if the next line is '}', then don't outdent
                if (listIter.hasNext()) {
                    String nextLine = listIter.next();
                    if (nextLine.startsWith("}")) { 
                        indentLevel++;
                    }

                    // set back to the previous element
                    listIter.previous();
                }
                indentLevel--;
            }
        }

        indentLevel--;
        OutputUtil.newLine(sb);
        OutputUtil.javaIndent(sb, indentLevel);
        sb.append('}');

        return sb.toString();
    }
}

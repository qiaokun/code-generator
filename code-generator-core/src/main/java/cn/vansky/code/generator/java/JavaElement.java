/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

import cn.vansky.code.generator.util.OutputUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * JAVA元素(类/字段)公共信息
 * 1: 注解, 2: 注解, 3: 作用域, 4: final, 5: static
 * Author: CK.
 * Date: 2015/6/6.
 */
public abstract class JavaElement {

    /** JAVA注释 */
    private List<String> javaDocLines;

    /** JAVA作用域 */
    private String javaScope = JavaKeywords.DEFAULT;

    /** static */
    private boolean isStatic;

    /** final */
    private boolean isFinal;

    /** JAVA注解 */
    private List<String> annotations;

    public JavaElement() {
        super();
        javaDocLines = new ArrayList<String>();
        annotations = new ArrayList<String>();
    }

    public JavaElement(JavaElement original) {
        this();
        this.annotations.addAll(original.annotations);
        this.isFinal = original.isFinal;
        this.isStatic = original.isFinal;
        this.javaDocLines.addAll(original.javaDocLines);
        this.javaScope = original.javaScope;
    }

    public List<String> getJavaDocLines() {
        return javaDocLines;
    }

    public void addJavaDocLine(String javaDocLine) {
        javaDocLines.add(javaDocLine);
    }

    protected void addFormattedJavadoc(StringBuilder sb, int indentLevel) {
        for (String javaDocLine : javaDocLines) {
            OutputUtil.javaIndent(sb, indentLevel);
            sb.append(javaDocLine);
            OutputUtil.newLine(sb);
        }
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(String annotation) {
        annotations.add(annotation);
    }

    public void addSuppressTypeWarningsAnnotation() {
        addAnnotation("@SuppressWarnings(\"unchecked\")"); 
    }

    protected void addFormattedAnnotations(StringBuilder sb, int indentLevel) {
        for (String annotation : annotations) {
            OutputUtil.javaIndent(sb, indentLevel);
            sb.append(annotation);
            OutputUtil.newLine(sb);
        }
    }

    public String getJavaScope() {
        return javaScope;
    }

    public void setJavaScope(String javaScope) {
        this.javaScope = javaScope;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    protected void addCommonFormatted(StringBuilder sb, int indentLevel) {
        // JAVA类注释
        addFormattedJavadoc(sb, indentLevel);
        // JAVA类注解
        addFormattedAnnotations(sb, indentLevel);

        OutputUtil.javaIndent(sb, indentLevel);
    }
}

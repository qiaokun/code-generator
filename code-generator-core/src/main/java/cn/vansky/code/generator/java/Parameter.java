/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数
 * Author: CK.
 * Date: 2015/6/6.
 */
public class Parameter {
    /** 参数名 */
    private String name;
    /** JAVA类型信息 */
    private JavaTypeInfo type;
    /** 是否数组 */
    private boolean isVarargs;
    /** 注解列表 */
    private List<String> annotations;

    public Parameter(JavaTypeInfo type, String name, boolean isVarargs) {
        this.name = name;
        this.type = type;
        this.isVarargs = isVarargs;
        annotations = new ArrayList<String>();
    }

    public Parameter(JavaTypeInfo type, String name) {
        this(type, name, false);
    }

    public Parameter(JavaTypeInfo type, String name, String annotation) {
        this(type, name, false);
        addAnnotation(annotation);
    }

    public Parameter(JavaTypeInfo type, String name, String annotation, boolean isVarargs) {
        this(type, name, isVarargs);
        addAnnotation(annotation);
    }

    public String getFormattedContent() {
        StringBuilder sb = new StringBuilder();

        for (String annotation : annotations) {
            sb.append(annotation);
            sb.append(' ');
        }

        sb.append(type.getShortName());
        sb.append(' ');
        if (isVarargs()) {
            sb.append(JavaKeywords.ARRAYS);
        }
        sb.append(name);

        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public JavaTypeInfo getType() {
        return type;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(String annotation) {
        annotations.add(annotation);
    }

    public boolean isVarargs() {
        return isVarargs;
    }

    @Override
    public String toString() {
        return getFormattedContent();
    }
}

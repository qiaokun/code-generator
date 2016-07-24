/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

import cn.vansky.code.generator.util.OutputUtil;

import java.util.*;

/**
 * 内部类信息
 * Author: CK
 * Date: 2015/6/7
 */
public class InnerClass extends JavaElement {

    /** 字段 */
    private List<Field> fields;

    /** 内部类 */
    private List<InnerClass> innerClasses;

    /** 内部枚举 */
    private List<InnerEnum> innerEnums;

    /** 继承父类 */
    private JavaTypeInfo superClass;

    /** 类型 */
    private JavaTypeInfo type;

    /** 实现接口 */
    private Set<JavaTypeInfo> superInterfaceTypes;

    /** 方法列表 */
    private List<Method> methods;

    /** 是否抽象类 */
    private boolean isAbstract;

    /** 是否接口 */
    private boolean isInterface;

    /** 初始化模块 */
    private List<InitializationBlock> initializationBlocks;

    public InnerClass(JavaTypeInfo type) {
        super();
        this.type = type;
        fields = new ArrayList<Field>();
        innerClasses = new ArrayList<InnerClass>();
        innerEnums = new ArrayList<InnerEnum>();
        superInterfaceTypes = new HashSet<JavaTypeInfo>();
        methods = new ArrayList<Method>();
        initializationBlocks = new ArrayList<InitializationBlock>();
    }

    public InnerClass(String typeName) {
        this(new JavaTypeInfo(typeName));
    }

    public List<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public JavaTypeInfo getSuperClass() {
        return superClass;
    }

    public void setSuperClass(JavaTypeInfo superClass) {
        this.superClass = superClass;
    }

    public void setSuperClass(String superClassType) {
        this.superClass = new JavaTypeInfo(superClassType);
    }

    public List<InnerClass> getInnerClasses() {
        return innerClasses;
    }

    public void addInnerClass(InnerClass innerClass) {
        innerClasses.add(innerClass);
    }

    public List<InnerEnum> getInnerEnums() {
        return innerEnums;
    }

    public void addInnerEnum(InnerEnum innerEnum) {
        innerEnums.add(innerEnum);
    }

    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        addCommonFormatted(sb, indentLevel);

        sb.append(getJavaScope());

        // 抽象类
        if (isAbstract()) {
            sb.append(JavaKeywords.ABSTRACT);
        }

        // 静态类
        if (isStatic()) {
            sb.append(JavaKeywords.STATIC);
        }

        // final类
        if (isFinal()) {
            sb.append(JavaKeywords.FINAL);
        }

        // 接口或者类
        sb.append(isInterface() ? JavaKeywords.INTERFACE: JavaKeywords.CLASS);

        // 类名
        sb.append(getType().getShortName());

        // 父类
        if (getSuperClass() != null) {
            sb.append(JavaKeywords.EXTENDS);
            sb.append(superClass.getShortName());
        }

        // 类实现的接口
        if (!isInterface()) {
            if (getSuperInterfaceTypes().size() > 0) {
                sb.append(JavaKeywords.IMPLEMENTS);

                boolean comma = false;
                for (JavaTypeInfo fqjt : getSuperInterfaceTypes()) {
                    if (comma) {
                        sb.append(", ");
                    } else {
                        comma = true;
                    }

                    sb.append(fqjt.getShortName());
                }
            }
        }

        sb.append(" {"); 
        indentLevel++;

        // 字段
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            OutputUtil.newLine(sb);
            sb.append(field.getFormattedContent(indentLevel));
            if (fields.size() - 1 != i) {
                OutputUtil.newLine(sb);
            }
        }

        if (initializationBlocks.size() > 0) {
            OutputUtil.newLine(sb);
        }

        // 静态初始化块
        for (int i = 0; i < initializationBlocks.size(); i++) {
            OutputUtil.newLine(sb);
            InitializationBlock initializationBlock = initializationBlocks.get(i);
            sb.append(initializationBlock.getFormattedContent(indentLevel));
            if (initializationBlocks.size() - 1 != i) {
                OutputUtil.newLine(sb);
            }
        }

        if (methods.size() > 0) {
            OutputUtil.newLine(sb);
        }

        // 方法块
        for (int i = 0; i < methods.size(); i++) {
            OutputUtil.newLine(sb);
            Method method = methods.get(i);
            sb.append(method.getFormattedContent(indentLevel, false));
            if (methods.size() - 1 != i) {
                OutputUtil.newLine(sb);
            }
        }

        if (innerClasses.size() > 0) {
            OutputUtil.newLine(sb);
        }

        // 内部类
        for (int i = 0; i < innerClasses.size(); i++) {
            OutputUtil.newLine(sb);
            InnerClass innerClass = innerClasses.get(i);
            sb.append(innerClass.getFormattedContent(indentLevel));
            if (innerClasses.size() - 1 != i) {
                OutputUtil.newLine(sb);
            }
        }

        if (innerEnums.size() > 0) {
            OutputUtil.newLine(sb);
        }

        // 内部枚举
        for (int i = 0; i < innerEnums.size(); i++) {
            OutputUtil.newLine(sb);
            InnerEnum innerEnum = innerEnums.get(i);
            sb.append(innerEnum.getFormattedContent(indentLevel));
            if (innerEnums.size() - 1 != i) {
                OutputUtil.newLine(sb);
            }
        }

        indentLevel--;
        OutputUtil.newLine(sb);
        OutputUtil.javaIndent(sb, indentLevel);
        sb.append('}');

        return sb.toString();
    }

    public Set<JavaTypeInfo> getSuperInterfaceTypes() {
        return superInterfaceTypes;
    }

    public void addSuperInterface(JavaTypeInfo superInterface) {
        superInterfaceTypes.add(superInterface);
    }

    public void addSuperInterface(String superInterface) {
        JavaTypeInfo tmpSuperInterface = new JavaTypeInfo(superInterface);
        superInterfaceTypes.add(tmpSuperInterface);
    }

    public void addSuperInterfaces(Set<JavaTypeInfo> superInterfaces) {
        superInterfaceTypes.addAll(superInterfaces);
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public JavaTypeInfo getType() {
        return type;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean isAbtract) {
        this.isAbstract = isAbtract;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean isInterface) {
        this.isInterface = isInterface;
    }
}

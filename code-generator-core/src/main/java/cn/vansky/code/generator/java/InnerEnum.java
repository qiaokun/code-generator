/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

import cn.vansky.code.generator.util.OutputUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 内部枚举
 * Author: CK
 * Date: 2015/6/7
 */
public class InnerEnum extends JavaElement {

    /** 字段 */
    private List<Field> fields;

    /** 内部类 */
    private List<InnerClass> innerClasses;

    /** 内部枚举 */
    private List<InnerEnum> innerEnums;

    /** JAVA类型 */
    private JavaTypeInfo type;

    /** 父接口 */
    private Set<JavaTypeInfo> superInterfaceTypes;

    /** 方法列表 */
    private List<Method> methods;

    /** 枚举常量 */
    private List<String> enumConstants;

    public InnerEnum(JavaTypeInfo type) {
        super();
        this.type = type;
        fields = new ArrayList<Field>();
        innerClasses = new ArrayList<InnerClass>();
        innerEnums = new ArrayList<InnerEnum>();
        superInterfaceTypes = new HashSet<JavaTypeInfo>();
        methods = new ArrayList<Method>();
        enumConstants = new ArrayList<String>();
    }

    public List<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.add(field);
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

    public List<String> getEnumConstants() {
        return enumConstants;
    }

    public void addEnumConstant(String enumConstant) {
        enumConstants.add(enumConstant);
    }

    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        addCommonFormatted(sb, indentLevel);

        if (getJavaScope() == JavaKeywords.PUBLIC) {
            sb.append(getJavaScope());
        }

        sb.append(JavaKeywords.ENUM);
        sb.append(getType().getShortName());

        if (superInterfaceTypes.size() > 0) {
            sb.append(JavaKeywords.IMPLEMENTS);

            boolean comma = false;
            for (JavaTypeInfo fqjt : superInterfaceTypes) {
                if (comma) {
                    sb.append(", "); 
                } else {
                    comma = true;
                }

                sb.append(fqjt.getShortName());
            }
        }

        sb.append(" {"); 
        indentLevel++;

        Iterator<String> strIter = enumConstants.iterator();
        while (strIter.hasNext()) {
            OutputUtil.newLine(sb);
            OutputUtil.javaIndent(sb, indentLevel);
            String enumConstant = strIter.next();
            sb.append(enumConstant);

            if (strIter.hasNext()) {
                sb.append(',');
            } else {
                sb.append(';');
            }
        }

        if (fields.size() > 0) {
            OutputUtil.newLine(sb);
        }

        Iterator<Field> fldIter = fields.iterator();
        while (fldIter.hasNext()) {
            OutputUtil.newLine(sb);
            Field field = fldIter.next();
            sb.append(field.getFormattedContent(indentLevel));
            if (fldIter.hasNext()) {
                OutputUtil.newLine(sb);
            }
        }

        if (methods.size() > 0) {
            OutputUtil.newLine(sb);
        }

        Iterator<Method> mtdIter = methods.iterator();
        while (mtdIter.hasNext()) {
            OutputUtil.newLine(sb);
            Method method = mtdIter.next();
            sb.append(method.getFormattedContent(indentLevel, false));
            if (mtdIter.hasNext()) {
                OutputUtil.newLine(sb);
            }
        }

        if (innerClasses.size() > 0) {
            OutputUtil.newLine(sb);
        }

        Iterator<InnerClass> icIter = innerClasses.iterator();
        while (icIter.hasNext()) {
            OutputUtil.newLine(sb);
            InnerClass innerClass = icIter.next();
            sb.append(innerClass.getFormattedContent(indentLevel));
            if (icIter.hasNext()) {
                OutputUtil.newLine(sb);
            }
        }

        if (innerEnums.size() > 0) {
            OutputUtil.newLine(sb);
        }

        Iterator<InnerEnum> ieIter = innerEnums.iterator();
        while (ieIter.hasNext()) {
            OutputUtil.newLine(sb);
            InnerEnum innerEnum = ieIter.next();
            sb.append(innerEnum.getFormattedContent(indentLevel));
            if (ieIter.hasNext()) {
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

    public List<Method> getMethods() {
        return methods;
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public JavaTypeInfo getType() {
        return type;
    }
}

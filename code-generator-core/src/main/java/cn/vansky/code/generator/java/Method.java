/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

import cn.vansky.code.generator.util.OutputUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * JAVA方法
 * 1: 注释, 2: 注解, 3: 作用域, 4: final, 5: static, 6: transient, 7: volatile, 8: 初始值
 * Author: CK.
 * Date: 2015/6/6.
 */
public class Method extends JavaElement {

    /** 包体行 */
    private List<String> bodyLines;

    /** 是否是构造器 */
    private boolean constructor;

    /** 返回值类型 */
    private JavaTypeInfo returnType;

    /** 方法名 */
    private String name;

    /** 参数列表 */
    private List<Parameter> parameters;

    /** 异常列表 */
    private List<JavaTypeInfo> exceptions;

    /** 是否同步 */
    private boolean isSynchronized;

    /** 是否本地方法 */
    private boolean isNative;

    public Method(String name) {
        super();
        bodyLines = new ArrayList<String>();
        parameters = new ArrayList<Parameter>();
        exceptions = new ArrayList<JavaTypeInfo>();
        this.name = name;
    }

    public Method(Method original) {
        super(original);
        bodyLines = new ArrayList<String>();
        parameters = new ArrayList<Parameter>();
        exceptions = new ArrayList<JavaTypeInfo>();
        this.bodyLines.addAll(original.bodyLines);
        this.constructor = original.constructor;
        this.exceptions.addAll(original.exceptions);
        this.name = original.name;
        this.parameters.addAll(original.parameters);
        this.returnType = original.returnType;
        this.isNative = original.isNative;
        this.isSynchronized = original.isSynchronized;
    }

    public String getFormattedContent(int indentLevel, boolean interfaceMethod) {
        StringBuilder sb = new StringBuilder();

        addCommonFormatted(sb, indentLevel);

        if (!interfaceMethod) {
            // 方法
            sb.append(getJavaScope());

            if (isStatic()) {
                sb.append(JavaKeywords.STATIC);
            }

            if (isFinal()) {
                sb.append(JavaKeywords.FINAL);
            }

            if (isSynchronized()) {
                sb.append(JavaKeywords.SYNCHRONIZED);
            }

            if (isNative()) {
                sb.append(JavaKeywords.NATIVE);
            } else if (bodyLines.size() == 0) {
                sb.append(JavaKeywords.ABSTRACT);
            }
        }

        if (!constructor) {
            // 返回值
            sb.append(getReturnType() == null ? JavaKeywords.VOID : getReturnType().getShortName());
            sb.append(' ');
        }

        sb.append(getName());
        sb.append('(');

        // 方法参数
        boolean comma = false;
        for (Parameter parameter : getParameters()) {
            if (comma) {
                sb.append(", ");
            } else {
                comma = true;
            }

            sb.append(parameter.getFormattedContent());
        }

        sb.append(')');

        if (getExceptions().size() > 0) {
            // 异常
            sb.append(JavaKeywords.THROWS);
            comma = false;
            // 添加异常
            for (JavaTypeInfo fqjt : getExceptions()) {
                if (comma) {
                    sb.append(", ");
                } else {
                    comma = true;
                }

                sb.append(fqjt.getShortName());
            }
        }

        if (bodyLines.size() == 0 || isNative()) {
            // 抽象方法或本地方法
            sb.append(';');
        } else {
            sb.append(" {");
            indentLevel++;

            ListIterator<String> listIter = bodyLines.listIterator();
            // 遍历包体行
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
        }

        return sb.toString();
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

    public boolean isConstructor() {
        return constructor;
    }

    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public JavaTypeInfo getReturnType() {
        return returnType;
    }

    public void setReturnType(JavaTypeInfo returnType) {
        this.returnType = returnType;
    }

    public List<JavaTypeInfo> getExceptions() {
        return exceptions;
    }

    public void addException(JavaTypeInfo exception) {
        exceptions.add(exception);
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    public void setSynchronized(boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    public boolean isNative() {
        return isNative;
    }

    public void setNative(boolean isNative) {
        this.isNative = isNative;
    }
}

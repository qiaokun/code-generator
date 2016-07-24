/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

/**
 * JAVA字段
 * 1: 注释, 2: 注解, 3: 作用域, 4: final, 5: static, 6: transient, 7: volatile, 8: 初始值
 * Author: CK.
 * Date: 2015/6/6.
 */
public class Field extends JavaElement {
    /** JAVA类型信息 */
    private JavaTypeInfo type;
    /** 字段名 */
    private String name;
    /** 初始值 */
    private String initializationString;
    /** transient */
    private boolean isTransient;
    /** volatile */
    private boolean isVolatile;

    public Field(String name, JavaTypeInfo type) {
        super();
        this.name = name;
        this.type = type;
    }

    public Field(Field field) {
        super(field);
        this.type = field.type;
        this.name = field.name;
        this.initializationString = field.initializationString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaTypeInfo getType() {
        return type;
    }

    public void setType(JavaTypeInfo type) {
        this.type = type;
    }

    public String getInitializationString() {
        return initializationString;
    }

    public void setInitializationString(String initializationString) {
        this.initializationString = initializationString;
    }

    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        addCommonFormatted(sb, indentLevel);

        sb.append(getJavaScope());

        if (isStatic()) {
            sb.append(JavaKeywords.STATIC);
        }

        if (isFinal()) {
            sb.append(JavaKeywords.FINAL);
        }

        if (isTransient()) {
            sb.append(JavaKeywords.TRANSIENT);
        }

        if (isVolatile()) {
            sb.append(JavaKeywords.VOLATILE);
        }

        sb.append(getType().getShortName());
        sb.append(' ');
        sb.append(getName());

        // 设置初始值
        if (isFinal() || getInitializationString() != null && getInitializationString().length() > 0) {
            sb.append(" = ");
            sb.append('"');
            sb.append(null == getInitializationString() ? "" : getInitializationString());
            sb.append('"');
        }

        sb.append(';');

        return sb.toString();
    }

    public boolean isTransient() {
        return isTransient;
    }

    public void setTransient(boolean isTransient) {
        this.isTransient = isTransient;
    }

    public boolean isVolatile() {
        return isVolatile;
    }

    public void setVolatile(boolean isVolatile) {
        this.isVolatile = isVolatile;
    }
}

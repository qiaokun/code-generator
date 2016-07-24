/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

/**
 * JAVA原始8种基础类型
 * Author: CK
 * Date: 2015/6/7
 */
public enum PrimitiveJavaType {
    booleanInstance("java.lang.Boolean", "booleanValue()", "boolean"),
    byteInstance("java.lang.Byte", "byteValue()", "byte"),
    characterInstance("java.lang.Character", "charValue()", "char"),
    doubleInstance("java.lang.Double", "doubleValue()", "double"),
    floatInstance("java.lang.Float", "floatValue()", "float"),
    integerInstance("java.lang.Integer", "intValue()", "int"),
    longInstance("java.lang.Long", "longValue()", "long"),
    shortInstance("java.lang.Short", "shortValue()", "short"),
    ;
    private String fullyQualifiedName;

    private JavaTypeInfo javaTypeInfo;

    /** 转换成基础类型的方法 */
    private String toPrimitiveMethod;

    private String shortName;

    private PrimitiveJavaType(String fullyQualifiedName, String toPrimitiveMethod, String shortName) {
        this.fullyQualifiedName = fullyQualifiedName;
        this.toPrimitiveMethod = toPrimitiveMethod;
        this.shortName = shortName;
        javaTypeInfo = new JavaTypeInfo(fullyQualifiedName);
    }

    public String getToPrimitiveMethod() {
        return toPrimitiveMethod;
    }

    public String getShortName() {
        return shortName;
    }

    public static PrimitiveJavaType get(String shortName) {
        PrimitiveJavaType [] primitiveJavaTypes = PrimitiveJavaType.values();
        for (PrimitiveJavaType javaType : primitiveJavaTypes) {
            if (javaType.getShortName().equals(shortName)) {
                return javaType;
            }
        }
        return null;
    }
}

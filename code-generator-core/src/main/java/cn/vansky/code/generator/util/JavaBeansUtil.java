/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.util;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.Field;
import cn.vansky.code.generator.java.JavaKeywords;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.JavaTypeInfoEnum;
import cn.vansky.code.generator.java.Method;
import cn.vansky.code.generator.java.Parameter;

import java.util.Locale;

/**
 * Bean扩展
 * Author: CK
 * Date: 2015/6/7
 */
public class JavaBeansUtil {

    /**
     * 获取Get方法名称
     * @param property 属性名
     * @param javaTypeInfo JAVA类型信息
     * @return Get方法名称
     */
    public static String getGetterMethodName(String property, JavaTypeInfo javaTypeInfo) {
        StringBuilder sb = new StringBuilder();

        sb.append(property);
        // 第一个字母小写
        if (Character.isLowerCase(sb.charAt(0))) {
            // 属性长度为1或者第二个字母小写
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }

        if (javaTypeInfo.equals(JavaTypeInfoEnum.BOOLEAN.getJavaTypeInfo())) {
            sb.insert(0, "is");
        } else {
            sb.insert(0, "get");
        }

        return sb.toString();
    }

    /**
     * 获取Set方法名称
     * @param property 属性名
     * @return Set方法名称
     */
    public static String getSetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();

        sb.append(property);
        // 第一个字母小写
        if (Character.isLowerCase(sb.charAt(0))) {
            // 属性长度为1或者第二个字母小写
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }

        sb.insert(0, "set");

        return sb.toString();
    }

    /**
     * 去掉特殊字符,自定义第一个字母是否大写
     * @param inputString 输入字符
     * @param firstCharacterUppercase 第一个字母是否大写标志
     * @return 字符串
     */
    public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            switch (c) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;
                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        return sb.toString();
    }

    /**
     * Trans data column to java column.
     *
     * @param name tableName or columnName
     * @param flag 1:tableName,2:columnName
     * @return java column
     */
    public static String dataBaseToJava(String name, int flag) {
        String[] array = name.replaceFirst("tb_", "").replaceFirst("td_", "").split("_");
        StringBuilder s = new StringBuilder();
        if (flag == 1) {
            for (String a : array) {
                s.append(a.substring(0, 1).toUpperCase() + a.substring(1, a.length()));
            }
        } else if (2 == flag) {
            s.append(array[0]);
            for (int i = 1; i < array.length; i++) {
                s.append(array[i].substring(0, 1).toUpperCase() + array[i].substring(1, array[i].length()));
            }
        }

        return s.toString();
    }

    /**
     * 获取有效的属性名称
     * 【eMail-eMail】 【firstName-firstName】 【URL-URL】 【XAxis-XAxis】 【a-a】 【B-b】 【Yaxis-yaxis】
     * @param inputString 输入的字符串
     * @return 有效的属性名称
     */
    public static String getValidPropertyName(String inputString) {
        String answer;

        if (inputString == null) {
            answer = null;
        } else if (inputString.length() < 2) {
            // 一个字符变小写
            answer = inputString.toLowerCase(Locale.US);
        } else {
            if (Character.isUpperCase(inputString.charAt(0)) && !Character.isUpperCase(inputString.charAt(1))) {
                // 第一个字符大写，第二个字母小写，需要改变第一个字母小写
                answer = inputString.substring(0, 1).toLowerCase(Locale.US) + inputString.substring(1);
            } else {
                answer = inputString;
            }
        }

        return answer;
    }

    /**
     * Get方法
     * @param field 字段
     * @return Get方法
     */
    public static Method getJavaBeansGetter(Field field) {
        Method method = new Method(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        method.setReturnType(field.getType());
        method.setJavaScope(JavaKeywords.PUBLIC);
        StringBuilder sb = new StringBuilder();
        sb.append(JavaKeywords.RETURN);
        sb.append(field.getName());
        sb.append(';');
        method.addBodyLine(sb.toString());
        return method;
    }

    /**
     * 获取Get方法
     * @param columnInfo 数据库列信息
     * @param tableInfoWrapper 数据库的表封装信息
     * @return Get方法
     */
    public static Method getJavaBeansGetter(ColumnInfo columnInfo, TableInfoWrapper tableInfoWrapper) {
        JavaTypeInfo javaTypeInfo = columnInfo.getJavaTypeInfo();
        String property = columnInfo.getJavaProperty();

        Method method = new Method(getGetterMethodName(property, javaTypeInfo));
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.setReturnType(javaTypeInfo);
        tableInfoWrapper.getCommentGenerator().addGetterComment(method, tableInfoWrapper, columnInfo);

        StringBuilder sb = new StringBuilder();
        sb.append(JavaKeywords.RETURN);
        sb.append(property);
        sb.append(';');
        method.addBodyLine(sb.toString());

        return method;
    }

    /**
     * 获取Set方法
     * @param columnInfo 数据库列信息
     * @param context 全局配置信息
     * @param tableInfoWrapper 数据库的表封装信息
     * @return Set方法
     */
    public static Method getJavaBeansSetter(ColumnInfo columnInfo,
                                            CodeGenContext context,
                                            TableInfoWrapper tableInfoWrapper) {
        JavaTypeInfo javaTypeInfo = columnInfo.getJavaTypeInfo();
        String property = columnInfo.getJavaProperty();

        Method method = new Method(getSetterMethodName(property));
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.addParameter(new Parameter(javaTypeInfo, property));
        tableInfoWrapper.getCommentGenerator().addSetterComment(method, tableInfoWrapper, columnInfo);

        StringBuilder sb = new StringBuilder();
        if (isTrimStringsEnabled(context) && columnInfo.isStringColumn()) {
            sb.append("this.");
            sb.append(property);
            sb.append(" = ");
            sb.append(property);
            sb.append(" == null ? null : ");
            sb.append(property);
            sb.append(".trim();");
            method.addBodyLine(sb.toString());
        } else {
            sb.append("this.");
            sb.append(property);
            sb.append(" = ");
            sb.append(property);
            sb.append(';');
            method.addBodyLine(sb.toString());
        }

        return method;
    }

    /**
     * 获取字段Bean
     * @param columnInfo 数据库列信息
     * @param tableInfoWrapper 数据库的表封装信息
     * @return Field
     */
    public static Field getJavaBeansField(ColumnInfo columnInfo, TableInfoWrapper tableInfoWrapper) {
        JavaTypeInfo javaTypeInfo = columnInfo.getJavaTypeInfo();
        String property = columnInfo.getJavaProperty();

        Field field = new Field(property, javaTypeInfo);
        field.setJavaScope(JavaKeywords.PRIVATE);
        tableInfoWrapper.getCommentGenerator().addFieldComment(field, tableInfoWrapper, columnInfo);

        return field;
    }

    /**
     * 获取字段常量信息
     * @param columnInfo 数据库列信息
     * @param tableInfoWrapper 数据库的表封装信息
     * @return Field
     */
    public static Field getJavaBeansFieldConstant(ColumnInfo columnInfo, TableInfoWrapper tableInfoWrapper) {
        String property = columnInfo.getJavaProperty();

        Field field = new Field("PROPERTY_" + property.toUpperCase(), JavaTypeInfoEnum.STRING.getJavaTypeInfo());
        field.setJavaScope(JavaKeywords.PRIVATE);
        field.setFinal(true);
        field.setStatic(true);
        field.setInitializationString(columnInfo.getJavaProperty());
        tableInfoWrapper.getCommentGenerator().addFieldComment(field, tableInfoWrapper, columnInfo);

        return field;
    }

    /**
     * 字段备注常量信息
     * @param columnInfo 数据库列信息
     * @param tableInfoWrapper 数据库的表封装信息
     * @return Field
     */
    public static Field getJavaBeansFieldRemark(ColumnInfo columnInfo, TableInfoWrapper tableInfoWrapper) {
        String property = columnInfo.getJavaProperty();

        Field field = new Field("PROPERTY_REMARK_" + property.toUpperCase(), JavaTypeInfoEnum.STRING.getJavaTypeInfo());
        field.setJavaScope(JavaKeywords.PRIVATE);
        field.setFinal(true);
        field.setStatic(true);
        field.setInitializationString(columnInfo.getComment());
        tableInfoWrapper.getCommentGenerator().addFieldComment(field, tableInfoWrapper, columnInfo);

        return field;
    }

    private static boolean isTrimStringsEnabled(CodeGenContext context) {
//        Properties properties = context.getJavaModelGeneratorConfiguration().getProperties();
//        boolean rc = StringUtil.isTrue(properties.getProperty(PropertyRegistry.MODEL_GENERATOR_TRIM_STRINGS));
        return false;
    }
}

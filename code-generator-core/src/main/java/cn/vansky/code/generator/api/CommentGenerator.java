/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.*;
import cn.vansky.code.generator.xml.XmlElement;

/**
 * 注释接口
 * Author: CK
 * Date: 2015/6/7
 */
public interface CommentGenerator {
    /**
     * 字段对应数据库列名注释
     *
     * @param field 字段
     * @param tableInfoWrapper  封装表信息
     * @param columnInfo 列信息
     */
    public void addFieldComment(Field field, TableInfoWrapper tableInfoWrapper, ColumnInfo columnInfo);

    /**
     * 字段注释
     *
     * @param field 字段
     * @param tableInfoWrapper 封装表信息
     */
    public void addFieldComment(Field field, TableInfoWrapper tableInfoWrapper);

    /**
     * JAVA类注释
     *
     * @param innerClass 类信息
     * @param tableInfoWrapper 封装表信息
     */
    public void addClassComment(InnerClass innerClass, TableInfoWrapper tableInfoWrapper);

    /**
     * 内部类注释
     *
     * @param innerClass 类信息
     * @param tableInfoWrapper 封装表信息
     * @param markAsDoNotDelete the mark as do not delete
     */
    public void addClassComment(InnerClass innerClass, TableInfoWrapper tableInfoWrapper, boolean markAsDoNotDelete);

    /**
     * 枚举注释
     *
     * @param innerEnum 枚举
     * @param tableInfoWrapper 封装表信息
     */
    public void addEnumComment(InnerEnum innerEnum, TableInfoWrapper tableInfoWrapper);

    /**
     * GET方法注释
     *
     * @param method 方法
     * @param tableInfoWrapper 封装表信息
     * @param columnInfo 列信息
     */
    public void addGetterComment(Method method, TableInfoWrapper tableInfoWrapper, ColumnInfo columnInfo);

    /**
     * SET方法注释
     *
     * @param method 方法
     * @param tableInfoWrapper 封装表信息
     * @param columnInfo 列信息
     */
    public void addSetterComment(Method method, TableInfoWrapper tableInfoWrapper, ColumnInfo columnInfo);

    /**
     * 自动生成方法注释
     *
     * @param method 方法
     * @param tableInfoWrapper 封装表信息
     */
    public void addGeneralMethodComment(Method method, TableInfoWrapper tableInfoWrapper);

    /**
     * JAVA文件注释
     *
     * @param compilationUnit 封装JAVA信息
     */
    public void addJavaFileComment(CompilationUnit compilationUnit);

    /**
     * 添加XML注释
     *
     * @param xmlElement XML元素
     */
    public void addComment(XmlElement xmlElement);
}

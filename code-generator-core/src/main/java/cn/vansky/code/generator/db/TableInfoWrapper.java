/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.db;

import cn.vansky.code.generator.api.CommentGenerator;
import cn.vansky.code.generator.api.file.GeneratedJavaFile;
import cn.vansky.code.generator.api.file.GeneratedXmlFile;
import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.config.rule.Rules;
import cn.vansky.code.generator.factory.RuleFactory;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.resolver.JavaTypeResolver;
import cn.vansky.code.generator.java.file.AbstractJavaGenerator;
import cn.vansky.code.generator.xml.mybatis.mapper.AbstractXmlMapperGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表信息封装
 * Author: CK.
 * Date: 2015/6/6.
 */
public abstract class TableInfoWrapper<T extends AbstractAttributes> {
    /** XML内容生成类 */
    protected AbstractXmlMapperGenerator<T> xmlMapperGenerator;
    /** JAVA文件生成类 */
    protected AbstractJavaGenerator<T> javaModelGenerators;
    /** 生成规则 */
    protected Rules rules;
    protected T attributes;
    /** 表信息 */
    protected TableInfo tableInfo;
    /** 全局信息 */
    protected CodeGenContext context;
    /** 主键列 */
    protected List<ColumnInfo> primaryKeyColumns;
    /** 基础数据库列 */
    protected List<ColumnInfo> baseColumns;
    /** The blob columns. */
    protected List<ColumnInfo> blobColumns;
    /** 表对应的列 */
    protected Map<String, List<ColumnInfo>> map;
    /** JAVA类型解析 */
    protected JavaTypeResolver javaTypeResolver;
    /** 注释规则 **/
    protected CommentGenerator commentGenerator;

    public TableInfoWrapper(CodeGenContext context) {
        this.context = context;
        this.map = new HashMap<String, List<ColumnInfo>>();
        this.primaryKeyColumns = new ArrayList<ColumnInfo>();
        this.baseColumns = new ArrayList<ColumnInfo>();
        this.blobColumns = new ArrayList<ColumnInfo>();
        this.rules = RuleFactory.getInstance().getRules(context.getRuleEnum());
        this.rules.setTableInfoWrapper(this);
        this.javaTypeResolver = context.getJavaTypeResolver();
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }

    public CodeGenContext getContext() {
        return context;
    }

    public void setContext(CodeGenContext context) {
        this.context = context;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public void addBaseColumn(ColumnInfo columnInfo) {
        this.baseColumns.add(columnInfo);
    }

    public void addPrimaryKeyColumn(ColumnInfo columnInfo) {
        this.primaryKeyColumns.add(columnInfo);
    }

    public Map<String, List<ColumnInfo>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<ColumnInfo>> map) {
        this.map = map;
    }

    public List<ColumnInfo> getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }

    public List<ColumnInfo> getBaseColumns() {
        return baseColumns;
    }

    public List<ColumnInfo> getBlobColumns() {
        return blobColumns;
    }

    public boolean hasPrimaryKeyColumns() {
        return primaryKeyColumns.size() > 0;
    }

    public boolean hasBLOBColumns() {
        return null != blobColumns && blobColumns.size() > 0;
    }

    public T getAttributes() {
        return attributes;
    }

    public List<ColumnInfo> getNonBLOBColumns() {
        List<ColumnInfo> answer = new ArrayList<ColumnInfo>();
        answer.addAll(primaryKeyColumns);
        answer.addAll(baseColumns);
        return answer;
    }

    public List<ColumnInfo> getAllColumns() {
        List<ColumnInfo> answer = new ArrayList<ColumnInfo>();
        answer.addAll(primaryKeyColumns);
        answer.addAll(baseColumns);
        answer.addAll(blobColumns);
        return answer;
    }

    public List<ColumnInfo> getNonPrimaryKeyColumns() {
        List<ColumnInfo> answer = new ArrayList<ColumnInfo>();
        answer.addAll(baseColumns);
        answer.addAll(blobColumns);
        return answer;
    }

    public List<ColumnInfo> getBLOBColumns() {
        List<ColumnInfo> answer = new ArrayList<ColumnInfo>();
        answer.addAll(blobColumns);
        return answer;
    }

    public void initialize() {
        // 计算JAVA类型
        calculateExtraColumnInformation();
        attributes.setTableInfoWrapper(this);
        attributes.calculateModelAttributes();
        attributes.calculateXmlAttributes();
    }

    /**
     * 解析JAVA类型/加入基础列信息
     */
    protected void calculateExtraColumnInformation() {
        List<ColumnInfo> pkList = new ArrayList<ColumnInfo>();
        List<ColumnInfo> blobList = new ArrayList<ColumnInfo>();
        List<ColumnInfo> columnInfoList = map.get(tableInfo.getIntrospectedTableName());
        for (ColumnInfo c : columnInfoList) {
            JavaTypeInfo javaTypeInfo = javaTypeResolver.calculateJavaType(c);
            if (javaTypeInfo != null) {
                c.setJavaTypeInfo(javaTypeInfo);
                c.setJdbcTypeName(javaTypeResolver.calculateJdbcTypeName(c));
            }
            this.addBaseColumn(c);
            for (ColumnInfo pk : primaryKeyColumns) {
                // 基本列中包括主键列
                if (pk.getActualColumnName().equals(c.getActualColumnName())) {
                    // 基本列中移除主键列
                    baseColumns.remove(c);
                    JavaTypeInfo pkJavaTypeInfo = javaTypeResolver.calculateJavaType(c);
                    if (pkJavaTypeInfo != null) {
                        c.setJavaTypeInfo(pkJavaTypeInfo);
                        c.setJdbcTypeName(javaTypeResolver.calculateJdbcTypeName(c));
                        // 把主键信息同步到临时对象中
                        pkList.add(c);
                        break;
                    }
                }
            }
            for (ColumnInfo blob : blobColumns) {
                // 基本列中包括主键列
                if (blob.getActualColumnName().equals(c.getActualColumnName())) {
                    // 基本列中移除主键列
                    baseColumns.remove(c);
                    JavaTypeInfo blobJavaTypeInfo = javaTypeResolver.calculateJavaType(c);
                    if (blobJavaTypeInfo != null) {
                        // 把主键信息同步到临时对象中
                        blobList.add(c);
                    }
                    break;
                }
            }
        }

        primaryKeyColumns.clear();
        primaryKeyColumns.addAll(pkList);

        blobColumns.clear();
        blobColumns.addAll(blobList);
    }

    /**
     * 验证JDBC DATE类型
     * @return true:false
     */
    public boolean hasJDBCDateColumns() {
        boolean rc = false;

        for (ColumnInfo columnInfo : primaryKeyColumns) {
            if (columnInfo.isJDBCDateColumn()) {
                rc = true;
                break;
            }
        }

        if (!rc) {
            for (ColumnInfo columnInfo : baseColumns) {
                if (columnInfo.isJDBCDateColumn()) {
                    rc = true;
                    break;
                }
            }
        }

        return rc;
    }

    /**
     * 验证JDBC TIME类型
     * @return true:false
     */
    public boolean hasJDBCTimeColumns() {
        boolean rc = false;

        for (ColumnInfo columnInfo : primaryKeyColumns) {
            if (columnInfo.isJDBCTimeColumn()) {
                rc = true;
                break;
            }
        }

        if (!rc) {
            for (ColumnInfo columnInfo : baseColumns) {
                if (columnInfo.isJDBCTimeColumn()) {
                    rc = true;
                    break;
                }
            }
        }

        return rc;
    }

    public CommentGenerator getCommentGenerator() {
        return commentGenerator;
    }

    public boolean isConstructorBased() {
        return false;
    }

    /**
     * 根据表自动生成XML文件
     *
     * @return the list of generated XML files for this table
     */
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        if (xmlMapperGenerator == null) {
            throw new RuntimeException("没找到相应的Mapper处理类");
        }

        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();
        getGeneratedXmlFiles(answer);
        return answer;
    }

    public abstract void getGeneratedXmlFiles(List<GeneratedXmlFile> answer);

    /**
     * 根据表自动生成JAVA文件
     *
     * @return the list of generated Java files for this table
     */
    public List<GeneratedJavaFile> getGeneratedJavaFiles() {
        if (javaModelGenerators == null) {
            throw new RuntimeException("没找到相应的java处理类");
        }
        List<CompilationUnit> compilationUnits = javaModelGenerators.getCompilationUnits();
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
        getGeneratedJavaFiles(answer, compilationUnits);
        return answer;
    }

    public abstract void getGeneratedJavaFiles(List<GeneratedJavaFile> answer,
                                               List<CompilationUnit> compilationUnits);
}

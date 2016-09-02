/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.config;

import cn.vansky.code.generator.api.formatter.DefaultJavaFormatter;
import cn.vansky.code.generator.api.formatter.DefaultXmlFormatter;
import cn.vansky.code.generator.api.formatter.JavaFormatter;
import cn.vansky.code.generator.api.formatter.XmlFormatter;
import cn.vansky.code.generator.db.DatabaseWrapper;
import cn.vansky.code.generator.factory.RuleFactory;
import cn.vansky.code.generator.java.resolver.DefaultJavaTypeResolverImpl;
import cn.vansky.code.generator.java.resolver.JavaTypeResolver;

import java.io.File;
import java.util.List;

/**
 * 全局信息类
 * Author: CK.
 * Date: 2015/6/6.
 */
public class CodeGenContext {
    /** 开始分隔符,mysql为反单引号(`) */
    private String beginningDelimiter;

    /** 结束分隔符,mysql为反单引号(`) */
    private String endingDelimiter;

    /** 数据库操作封装 */
    private DatabaseWrapper databaseWrapper;

    /** 默认JAVA格式化 */
    private JavaFormatter javaFormatter;

    /** 默认XML格式化 */
    private XmlFormatter xmlFormatter;

    /** JAVA类型解析 */
    private JavaTypeResolver javaTypeResolver;

    private String generatorType;

    /** 默认规则类 */
    private RuleFactory.RuleEnum ruleEnum;

    /** 数据库URL */
    private String url;

    /** 数据库用户名 */
    private String user;

    /** 数据库密码 */
    private String password;

    /** 数据库名 */
    private String database;

    /** 项目名 */
    private String projectName;

    /** 类路径 */
    private String classpath;

    /** 页面需要生成的包括主键的表 */
    private List<String> tableNameList;

    /** 文件输出目录 */
    private String out;

    /** 生成文件目录 */
    private File file;

    /** 事务标志，1：XML配置，2：注解配置 */
    private Integer transactionType;

    /** 数据源名称 */
    private String transactionName;

    /** 是否添加分隔符 */
    private Integer isColumnNameDelimited;

    public CodeGenContext() {
        this.databaseWrapper = new DatabaseWrapper(this);
        this.javaFormatter = new DefaultJavaFormatter();
        this.xmlFormatter = new DefaultXmlFormatter();
        this.javaTypeResolver = new DefaultJavaTypeResolverImpl();
        this.ruleEnum = RuleFactory.RuleEnum.BASE_RULE;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setDatabaseByUrl(String url) {
        int i = url.lastIndexOf("/");
        setDatabase(url.substring(i + 1));
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public List<String> getTableNameList() {
        return tableNameList;
    }

    public void setTableNameList(List<String> tableNameList) {
        this.tableNameList = tableNameList;
    }

    public String getTargetPackage() {
        return this.getClasspath() + "." + this.getProjectName();
    }

    public JavaFormatter getJavaFormatter() {
        return javaFormatter;
    }

    public void setJavaFormatter(JavaFormatter javaFormatter) {
        this.javaFormatter = javaFormatter;
    }

    public XmlFormatter getXmlFormatter() {
        return xmlFormatter;
    }

    public void setXmlFormatter(XmlFormatter xmlFormatter) {
        this.xmlFormatter = xmlFormatter;
    }

    public JavaTypeResolver getJavaTypeResolver() {
        return javaTypeResolver;
    }

    public void setJavaTypeResolver(JavaTypeResolver javaTypeResolver) {
        this.javaTypeResolver = javaTypeResolver;
    }

    public String getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    public RuleFactory.RuleEnum getRuleEnum() {
        return ruleEnum;
    }

    public void setRuleEnum(RuleFactory.RuleEnum ruleEnum) {
        this.ruleEnum = ruleEnum;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public DatabaseWrapper getDatabaseWrapper() {
        return databaseWrapper;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getBeginningDelimiter() {
        return beginningDelimiter;
    }

    public void setBeginningDelimiter(String beginningDelimiter) {
        this.beginningDelimiter = beginningDelimiter;
    }

    public String getEndingDelimiter() {
        return endingDelimiter;
    }

    public void setEndingDelimiter(String endingDelimiter) {
        this.endingDelimiter = endingDelimiter;
    }

    public Integer getIsColumnNameDelimited() {
        return isColumnNameDelimited;
    }

    public void setIsColumnNameDelimited(Integer isColumnNameDelimited) {
        this.isColumnNameDelimited = isColumnNameDelimited;
    }
}

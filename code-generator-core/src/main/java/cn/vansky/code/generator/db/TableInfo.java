/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.db;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.util.StringUtility;
import org.apache.commons.lang.StringUtils;

/**
 * 表信息
 * Author: CK.
 * Date: 2015/6/6.
 */
public class TableInfo {
    /** 全局变量 */
    CodeGenContext context;

    /** 默认目录名称 */
    private String introspectedCatalog;

    /** The introspected schema. */
    private String introspectedSchema;

    /** 默认表名 */
    private String introspectedTableName;

    /** 运行时目录名称 */
    private String runtimeCatalog;

    /** The runtime schema. */
    private String runtimeSchema;

    /** 运行时表名 */
    private String runtimeTableName;

    /** 对象名称 */
    private String domainObjectName;

    /** 对象对应的包名 */
    private String domainObjectSubPackage;

    /** 别名 */
    private String alias;
    /** 表注释 */
    private String remarks;

    /** 生成的SQL中的表名将不会包含schema和catalog前缀 */
    private boolean ignoreQualifiersAtRuntime = true;

    public void setContext(CodeGenContext context) {
        this.context = context;
    }

    public CodeGenContext getContext() {
        return context;
    }

    public String getIntrospectedCatalog() {
        return introspectedCatalog;
    }

    public void setIntrospectedCatalog(String introspectedCatalog) {
        this.introspectedCatalog = introspectedCatalog;
    }

    public String getIntrospectedSchema() {
        return introspectedSchema;
    }

    public void setIntrospectedSchema(String introspectedSchema) {
        this.introspectedSchema = introspectedSchema;
    }

    public String getIntrospectedTableName() {
        return introspectedTableName;
    }

    public void setIntrospectedTableName(String introspectedTableName) {
        this.introspectedTableName = introspectedTableName;
    }

    public String getRuntimeCatalog() {
        return runtimeCatalog;
    }

    public void setRuntimeCatalog(String runtimeCatalog) {
        this.runtimeCatalog = runtimeCatalog;
    }

    public String getRuntimeSchema() {
        return runtimeSchema;
    }

    public void setRuntimeSchema(String runtimeSchema) {
        this.runtimeSchema = runtimeSchema;
    }

    public String getRuntimeTableName() {
        return runtimeTableName;
    }

    public void setRuntimeTableName(String runtimeTableName) {
        this.runtimeTableName = runtimeTableName;
    }

    public String getDomainObjectName() {
        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName) {
        this.domainObjectName = domainObjectName;
    }

    public String getDomainObjectSubPackage() {
        return domainObjectSubPackage;
    }

    public void setDomainObjectSubPackage(String domainObjectSubPackage) {
        this.domainObjectSubPackage = domainObjectSubPackage;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isIgnoreQualifiersAtRuntime() {
        return ignoreQualifiersAtRuntime;
    }

    public void setIgnoreQualifiersAtRuntime(boolean ignoreQualifiersAtRuntime) {
        this.ignoreQualifiersAtRuntime = ignoreQualifiersAtRuntime;
    }

    /**
     * 添加表别名
     * @return 表别名
     */
    public String getAliasedFullyQualifiedTableNameAtRuntime() {
        StringBuilder sb = new StringBuilder();

        sb.append(getFullyQualifiedTableNameAtRuntime());

        if (StringUtils.isNotBlank(alias)) {
            sb.append(' ');
            sb.append(alias);
        }

        return sb.toString();
    }

    /**
     * 获取运行时全表名
     *
     * @return 运行时全表名
     */
    public String getFullyQualifiedTableNameAtRuntime() {
        StringBuilder localCatalog = new StringBuilder();
        if (!ignoreQualifiersAtRuntime) {
            if (StringUtils.isNotBlank(runtimeCatalog)) {
                localCatalog.append(runtimeCatalog);
            } else if (StringUtils.isNotBlank(introspectedCatalog)) {
                localCatalog.append(introspectedCatalog);
            }
        }
        if (localCatalog.length() > 0) {
            addDelimiters(localCatalog);
        }

        StringBuilder localSchema = new StringBuilder();
        if (!ignoreQualifiersAtRuntime) {
            if (StringUtils.isNotBlank(runtimeSchema)) {
                localSchema.append(runtimeSchema);
            } else if (StringUtils.isNotBlank(introspectedSchema)) {
                localSchema.append(introspectedSchema);
            }
        }
        if (localSchema.length() > 0) {
            addDelimiters(localSchema);
        }

        StringBuilder localTableName = new StringBuilder();
        if (StringUtils.isNotBlank(runtimeTableName)) {
            localTableName.append(runtimeTableName);
        } else {
            localTableName.append(introspectedTableName);
        }
        addDelimiters(localTableName);

        return StringUtility.composeFullyQualifiedTableName(localCatalog.toString(),
                localSchema.toString(), localTableName.toString(), '.');
    }

    /**
     * 表名“前后”添加分隔符
     * @param sb
     */
    private void addDelimiters(StringBuilder sb) {
        if (StringUtils.isNotBlank(context.getBeginningDelimiter())) {
            sb.insert(0, context.getBeginningDelimiter());
        }

        if (StringUtils.isNotBlank(context.getEndingDelimiter())) {
            sb.append(context.getEndingDelimiter());
        }
    }
}

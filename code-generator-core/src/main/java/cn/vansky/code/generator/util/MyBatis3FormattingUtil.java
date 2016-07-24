/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.util;

import cn.vansky.code.generator.db.ColumnInfo;
import org.apache.commons.lang.StringUtils;

/**
 * MyBatis3扩展
 * Author: CK.
 * Date: 2015/6/6.
 */
public class MyBatis3FormattingUtil {
    /**
     * Gets the parameter clause.
     *
     * @param columnInfo
     *            the introspected column
     * @return the parameter clause
     */
    public static String getParameterClause(ColumnInfo columnInfo) {
        return getParameterClause(columnInfo, null);
    }

    /**
     * Gets the parameter clause.
     *
     * @param columnInfo 列信息
     * @param prefix 前缀
     * @return 参数、类型、处理类
     */
    public static String getParameterClause(ColumnInfo columnInfo, String prefix) {
        StringBuilder sb = new StringBuilder();

        sb.append("#{");
        sb.append(columnInfo.getJavaProperty(prefix));
        sb.append(",jdbcType=");
        sb.append(columnInfo.getJdbcTypeName());

        if (StringUtils.isNotBlank(columnInfo.getTypeHandler())) {
            sb.append(",typeHandler=");
            sb.append(columnInfo.getTypeHandler());
        }

        sb.append('}');

        return sb.toString();
    }

    /**
     * Gets the parameter clause.
     *
     * @param columnInfo 列信息
     * @param prefix 前缀
     * @param alias 别名
     * @return 参数、类型、处理类
     */
    public static String getParameterClauseByAlias(ColumnInfo columnInfo, String prefix, String alias) {
        StringBuilder sb = new StringBuilder();

        sb.append("#{");
        sb.append(alias + "." + columnInfo.getJavaProperty(prefix));
        sb.append(",jdbcType=");
        sb.append(columnInfo.getJdbcTypeName());

        if (StringUtils.isNotBlank(columnInfo.getTypeHandler())) {
            sb.append(",typeHandler=");
            sb.append(columnInfo.getTypeHandler());
        }

        sb.append('}');

        return sb.toString();
    }

    public static String getParameterClause(ColumnInfo columnInfo, String prefix, String like) {
        StringBuilder sb = new StringBuilder();

        sb.append("${");
        sb.append(columnInfo.getJavaProperty(prefix));
        sb.append(",jdbcType=");
        sb.append(columnInfo.getJdbcTypeName());

        if (StringUtils.isNotBlank(columnInfo.getTypeHandler())) {
            sb.append(",typeHandler=");
            sb.append(columnInfo.getTypeHandler());
        }

        sb.append('}');

        return sb.toString();
    }

    /**
     * The phrase to use in a select list. If there is a table alias, the value will be
     * "alias.columnName as alias_columnName"
     *
     * @param columnInfo
     *            the introspected column
     * @return the proper phrase
     */
    public static String getSelectListPhrase(ColumnInfo columnInfo) {
        if (StringUtils.isNotBlank(columnInfo.getTableAlias())) {
            StringBuilder sb = new StringBuilder();

            sb.append(getAliasedEscapedColumnName(columnInfo));
            sb.append(" as ");
            if (StringUtils.isNotBlank(columnInfo.getTableInfo().getContext().getBeginningDelimiter())) {
                sb.append(columnInfo.getTableInfo().getContext().getBeginningDelimiter());
            }
            sb.append(columnInfo.getTableAlias());
            sb.append('_');
            sb.append(columnInfo.getActualColumnName());
            if (StringUtils.isNotBlank(columnInfo.getTableInfo().getContext().getEndingDelimiter())) {
                sb.append(columnInfo.getTableInfo().getContext().getEndingDelimiter());
            }
            return sb.toString();
        } else {
            return getEscapedColumnName(columnInfo);
        }
    }

    /**
     * Gets the escaped column name.
     *
     * @param columnInfo
     *            the introspected column
     * @return the escaped column name
     */
    public static String getEscapedColumnName(ColumnInfo columnInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(columnInfo.getActualColumnName());

        if (StringUtils.isNotBlank(columnInfo.getTableInfo().getContext().getBeginningDelimiter())
                && StringUtils.isNotBlank(columnInfo.getTableInfo().getContext().getEndingDelimiter())) {
            sb.insert(0, columnInfo.getTableInfo().getContext().getBeginningDelimiter());
            sb.append(columnInfo.getTableInfo().getContext().getEndingDelimiter());
        }

        return sb.toString();
    }

    /**
     * Calculates the string to use in select phrases in SqlMaps.
     *
     * @param columnInfo
     *            the introspected column
     * @return the aliased escaped column name
     */
    public static String getAliasedEscapedColumnName(ColumnInfo columnInfo) {
        if (StringUtils.isNotBlank(columnInfo.getTableAlias())) {
            StringBuilder sb = new StringBuilder();
            sb.append(columnInfo.getTableAlias());
            sb.append('.');
            sb.append(getEscapedColumnName(columnInfo));
            return sb.toString();
        } else {
            return getEscapedColumnName(columnInfo);
        }
    }

    /**
     * The aliased column name for a select statement generated by the example clauses. This is not appropriate for
     * selects in SqlMaps because the column is not escaped for MyBatis. If there is a table alias, the value will be
     * alias.columnName.
     *
     * This method is used in the Example classes and the returned value will be in a Java string. So we need to escape
     * double quotes if they are the delimiters.
     *
     * @param columnInfo
     *            the introspected column
     * @return the aliased column name
     */
    public static String getAliasedActualColumnName(ColumnInfo columnInfo) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(columnInfo.getTableAlias())) {
            sb.append(columnInfo.getTableAlias());
            sb.append('.');
        }

        if (StringUtils.isNotBlank(columnInfo.getTableInfo().getContext().getBeginningDelimiter())) {
            sb.append(columnInfo.getTableInfo().getContext().getBeginningDelimiter());
        }

        sb.append(columnInfo.getActualColumnName());

        if (StringUtils.isNotBlank(columnInfo.getTableInfo().getContext().getEndingDelimiter())) {
            sb.append(columnInfo.getTableInfo().getContext().getEndingDelimiter());
        }

        return sb.toString();
    }

    /**
     * The renamed column name for a select statement. If there is a table alias, the value will be alias_columnName.
     * This is appropriate for use in a result map.
     *
     * @param columnInfo
     *            the introspected column
     * @return the renamed column name
     */
    public static String getRenamedColumnNameForResultMap(ColumnInfo columnInfo) {
        if (StringUtils.isNotBlank(columnInfo.getTableAlias())) {
            StringBuilder sb = new StringBuilder();
            sb.append(columnInfo.getTableAlias());
            sb.append('_');
            sb.append(columnInfo.getActualColumnName());
            return sb.toString();
        } else {
            return columnInfo.getActualColumnName();
        }
    }

    /**
     * PPMS专用
     * @param columnInfo 列信息
     * @return
     */
    public static String getFindPageParameterClause(ColumnInfo columnInfo) {
        StringBuilder sb = new StringBuilder();

        sb.append("#{");
        sb.append("obj.");
        sb.append(columnInfo.getJavaProperty(null));
        sb.append(",jdbcType=");
        sb.append(columnInfo.getJdbcTypeName());

        if (StringUtils.isNotBlank(columnInfo.getTypeHandler())) {
            sb.append(",typeHandler=");
            sb.append(columnInfo.getTypeHandler());
        }

        sb.append('}');

        return sb.toString();
    }
}

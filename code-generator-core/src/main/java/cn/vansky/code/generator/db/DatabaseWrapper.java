/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.db;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.factory.TableInfoWrapperFactory;
import cn.vansky.framework.common.util.DbManager;
import cn.vansky.framework.common.util.Tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作封装
 * Author: CK
 * Date: 2015/6/14
 */
public class DatabaseWrapper {

    public DatabaseWrapper(CodeGenContext context) {
        this.context = context;
    }

    private CodeGenContext context;

    /**
     * 创建表信息
     */
    public <T extends AbstractAttributes> List<TableInfoWrapper<T>> createTableInfoWrapper() {
        /** 表信息封装列表 */
        List<TableInfoWrapper<T>> tableInfoWrappers = new ArrayList<TableInfoWrapper<T>>();
        DbManager dbManager = new DbManager(context.getUrl(), context.getUser(), context.getPassword());
        Connection connection = dbManager.getConnection();
        DatabaseMetaData databaseMetaData = dbManager.getDatabaseMetaData(connection);
        try {
            setDelimiter(databaseMetaData, context);
            dealKeyTableList(dbManager, connection, tableInfoWrappers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbManager.close(connection);
        }
        return tableInfoWrappers;
    }

    private <T extends AbstractAttributes> void dealKeyTableList(DbManager dbManager, Connection connection, List<TableInfoWrapper<T>> tableInfoWrappers) throws SQLException {
        List<String> tableNames = context.getTableNameList();
        if (null == tableNames) {
            return;
        }
        ResultSet dbTables = dbManager.getTables(connection, null, context.getDatabase(), null);
        while (dbTables.next()) {
            for (String tableName : tableNames) {
                String dbTableName = dbTables.getString("TABLE_NAME");
                if (dbTableName.equalsIgnoreCase(tableName)) {
                    Map<String, List<ColumnInfo>> answer = new HashMap<String, List<ColumnInfo>>();
                    List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
                    TableInfoWrapper tableInfoWrapper = TableInfoWrapperFactory.getInstance().getTableInfoWrapper(context);
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setIntrospectedCatalog(dbTables.getString("TABLE_CAT"));
                    tableInfo.setIntrospectedSchema(dbTables.getString("TABLE_SCHEM"));
                    tableInfo.setIntrospectedTableName(dbTables.getString("TABLE_NAME"));
                    tableInfo.setRemarks(dbTables.getString("REMARKS"));
                    tableInfo.setDomainObjectName(Tools.dataBaseToJava(tableName.toLowerCase(), 1));
                    tableInfo.setDomainObjectSubPackage(Tools.dataBaseToJava(tableName.toLowerCase(), 2));
                    tableInfo.setContext(context);
                    tableInfoWrapper.setTableInfo(tableInfo);
                    tableInfoWrapper.setMap(answer);
                    tableInfoWrappers.add(tableInfoWrapper);
                    ResultSet rs = dbManager.getColumns(connection, null, context.getDatabase(), tableName);
                    while (rs.next()) {
                        ColumnInfo columnInfo = new ColumnInfo();
                        columnInfo.setActualColumnName(rs.getString("COLUMN_NAME"));
                        columnInfo.setJdbcType(rs.getInt("DATA_TYPE"));
                        columnInfo.setJdbcTypeName(rs.getString("TYPE_NAME"));
                        columnInfo.setComment(rs.getString("REMARKS"));
                        columnInfo.setLength(rs.getInt("COLUMN_SIZE"));
                        columnInfo.setNullable(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
                        columnInfo.setScale(rs.getInt("DECIMAL_DIGITS"));
                        columnInfo.setDefaultValue(rs.getString("COLUMN_DEF"));
                        if ("YES".equals(rs.getString("IS_AUTOINCREMENT"))) {
                            columnInfo.setIdentity(true);
                        }
                        columnInfo.setJavaProperty(Tools.getValidPropertyName(Tools.dataBaseToJava(columnInfo
                                .getActualColumnName().toLowerCase(), 2)));
                        columns.add(columnInfo);
                        columnInfo.setTableInfo(tableInfo);
                    }

                    answer.put(dbTableName, columns);

                    ResultSet pk = dbManager.getPrimaryKeys(connection, null, context.getDatabase(), tableName);
                    while (pk.next()) {
                        ColumnInfo columnInfo = new ColumnInfo();
                        columnInfo.setActualColumnName(pk.getString("COLUMN_NAME"));
                        columnInfo.setKeySeq(pk.getShort("KEY_SEQ"));
                        columnInfo.setPkName(pk.getString("PK_NAME"));
                        columnInfo.setJavaProperty(Tools.getValidPropertyName(Tools.dataBaseToJava(columnInfo
                                .getActualColumnName().toLowerCase(), 2)));
                        columnInfo.setTableInfo(tableInfo);
                        tableInfoWrapper.addPrimaryKeyColumn(columnInfo);
                    }
                }
            }
        }
    }

    public void setDelimiter(DatabaseMetaData databaseMetaData, CodeGenContext context)
            throws SQLException {
        if (1 == context.getIsColumnNameDelimited()) {
            DelimiterSQL dialects = DelimiterSQL.getDatabaseDialect(databaseMetaData.getDatabaseProductName());
            context.setBeginningDelimiter(dialects.getBegin());
            context.setEndingDelimiter(dialects.getEnd());
        }
    }
}

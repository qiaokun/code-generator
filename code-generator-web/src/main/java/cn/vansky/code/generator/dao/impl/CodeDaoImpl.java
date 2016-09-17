/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.dao.impl;

import cn.vansky.code.generator.dao.CodeDao;
import cn.vansky.code.generator.vo.CodeVo;
import cn.vansky.code.generator.vo.TableName;
import cn.vansky.framework.common.util.DbManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/3/27.
 */
@Repository("codeDao")
public class CodeDaoImpl implements CodeDao {

    public List<TableName> selectTableName(CodeVo codeVo) {
        List<TableName> list = new ArrayList<TableName>();
        DbManager dbManager = new DbManager(codeVo.getUrl(), codeVo.getUser(), codeVo.getPassword());
        Connection connection = dbManager.getConnection();
        String selectSql = "SELECT * FROM `information_schema`.`TABLES` t WHERE t.`TABLE_SCHEMA` = ?";
        PreparedStatement preparedStatement = dbManager.getPreparedStatement(connection, selectSql);
        ResultSet resultSet = null;
        try {
            String url = codeVo.getUrl();
            String database = url.substring(url.lastIndexOf("/") + 1, url.length());
            preparedStatement.setString(1, database);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TableName tableName = new TableName();
                tableName.setTableName(resultSet.getString("TABLE_NAME"));
                list.add(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close(connection, preparedStatement, resultSet);
        }
        return list;
    }
}

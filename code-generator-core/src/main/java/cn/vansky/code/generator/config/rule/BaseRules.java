/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.config.rule;

import cn.vansky.code.generator.db.TableInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.JavaTypeInfo;

/**
 * 基本规则
 * Author: CK.
 * Date: 2015/6/6.
 */
public class BaseRules implements Rules {

    /** 封装表信息 */
    protected TableInfoWrapper tableInfoWrapper;

    @Override
    public boolean generateInsert() {
        return true;
    }

    @Override
    public boolean generateInsertBatch() {
        return true;
    }

    @Override
    public boolean generateInsertSelective() {
        return true;
    }

    @Override
    public JavaTypeInfo calculateAllFieldsClass() {
        return null;
    }

    @Override
    public boolean generateUpdateByPrimaryKeyWithoutBLOBs() {
        return tableInfoWrapper.getPrimaryKeyColumns().size() > 0;
    }

    @Override
    public boolean generateUpdateByWhereEqualWithoutBLOBs() {
        return false;
    }

    @Override
    public boolean generateUpdateByPrimaryKeyWithBLOBs() {
        return tableInfoWrapper.getPrimaryKeyColumns().size() > 0 && false;
    }

    @Override
    public boolean generateUpdateByPrimaryKeySelective() {
        return tableInfoWrapper.getPrimaryKeyColumns().size() > 0;
    }

    @Override
    public boolean generateUpdateByWhereEqualSelective() {
        return false;
    }

    @Override
    public boolean generateDeleteByPrimaryKey() {
        return tableInfoWrapper.getPrimaryKeyColumns().size() > 0;
    }

    @Override
    public boolean generateDeleteByWhereEqual() {
        return false;
    }

    @Override
    public boolean generateDeleteByExample() {
        return false;
    }

    @Override
    public boolean generateBaseResultMap() {
        return true;
    }

    @Override
    public boolean generateResultMapWithBLOBs() {
        return false;
    }

    @Override
    public boolean generateSQLExampleWhereClause() {
        return false;
    }

    @Override
    public boolean generateMyBatis3UpdateByExampleWhereClause() {
        return false;
    }

    @Override
    public boolean generateBaseColumnList() {
        return true;
    }

    @Override
    public boolean generateBlobColumnList() {
        return false;
    }

    @Override
    public boolean generateBaseColumnEqual() {
        return true;
    }

    @Override
    public boolean generateBaseColumnLike() {
        return false;
    }

    @Override
    public boolean generateSelectByPrimaryKey() {
        boolean rc =  tableInfoWrapper.getPrimaryKeyColumns().size() > 0
                && (tableInfoWrapper.getBaseColumns().size() > 0
                || tableInfoWrapper.getBlobColumns().size() > 0);
        return rc;
    }

    @Override
    public boolean generateSelectByBean() {
        return false;
    }

    @Override
    public boolean generateSelectAllByExample() {
        return true;
    }

    @Override
    public boolean generateSelectByExampleWithoutBLOBs() {
        return false;
    }

    @Override
    public boolean generateSelectByExampleWithBLOBs() {
        return false;
    }

    @Override
    public boolean generateExampleClass() {
        return false;
    }

    @Override
    public boolean generateCountByExample() {
        return false;
    }

    @Override
    public boolean generateCount() {
        return true;
    }

    @Override
    public boolean generateUpdateByExampleSelective() {
        return false;
    }

    @Override
    public boolean generateUpdateByExampleWithoutBLOBs() {
        return false;
    }

    @Override
    public boolean generateUpdateByExampleWithBLOBs() {
        return false;
    }

    @Override
    public boolean generatePrimaryKeyClass() {
        return false;
    }

    @Override
    public boolean generateBaseRecordClass() {
        return true;
    }

    @Override
    public boolean generateRecordWithBLOBsClass() {
        return false;
    }

    @Override
    public boolean generateJavaClient() {
        return false;
    }

    @Override
    public TableInfo getIntrospectedTable() {
        return null;
    }

    public void setTableInfoWrapper(TableInfoWrapper tableInfoWrapper) {
        this.tableInfoWrapper = tableInfoWrapper;
    }
}

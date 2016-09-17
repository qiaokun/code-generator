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

    public boolean generateInsert() {
        return true;
    }

    public boolean generateInsertBatch() {
        return true;
    }

    public boolean generateInsertSelective() {
        return true;
    }

    public JavaTypeInfo calculateAllFieldsClass() {
        return null;
    }

    public boolean generateUpdateByPrimaryKeyWithoutBLOBs() {
        return tableInfoWrapper.getPrimaryKeyColumns().size() > 0;
    }

    public boolean generateUpdateByWhereEqualWithoutBLOBs() {
        return false;
    }

    public boolean generateUpdateByPrimaryKeyWithBLOBs() {
        return tableInfoWrapper.getPrimaryKeyColumns().size() > 0 && false;
    }

    public boolean generateUpdateByPrimaryKeySelective() {
        return tableInfoWrapper.getPrimaryKeyColumns().size() > 0;
    }

    public boolean generateUpdateByWhereEqualSelective() {
        return false;
    }

    public boolean generateDeleteByPrimaryKey() {
        return tableInfoWrapper.getPrimaryKeyColumns().size() > 0;
    }

    public boolean generateDeleteByWhereEqual() {
        return false;
    }

    public boolean generateDeleteByExample() {
        return false;
    }

    public boolean generateBaseResultMap() {
        return true;
    }

    public boolean generateResultMapWithBLOBs() {
        return false;
    }

    public boolean generateSQLExampleWhereClause() {
        return false;
    }

    public boolean generateMyBatis3UpdateByExampleWhereClause() {
        return false;
    }

    public boolean generateBaseColumnList() {
        return true;
    }

    public boolean generateBlobColumnList() {
        return false;
    }

    public boolean generateBaseColumnEqual() {
        return true;
    }

    public boolean generateBaseColumnLike() {
        return false;
    }

    public boolean generateSelectByPrimaryKey() {
        boolean rc =  tableInfoWrapper.getPrimaryKeyColumns().size() > 0
                && (tableInfoWrapper.getBaseColumns().size() > 0
                || tableInfoWrapper.getBlobColumns().size() > 0);
        return rc;
    }

    public boolean generateSelectByBean() {
        return false;
    }

    public boolean generateSelectAllByExample() {
        return true;
    }

    public boolean generateSelectByExampleWithoutBLOBs() {
        return false;
    }

    public boolean generateSelectByExampleWithBLOBs() {
        return false;
    }

    public boolean generateExampleClass() {
        return false;
    }

    public boolean generateCountByExample() {
        return false;
    }

    public boolean generateCount() {
        return true;
    }

    public boolean generateUpdateByExampleSelective() {
        return false;
    }

    public boolean generateUpdateByExampleWithoutBLOBs() {
        return false;
    }

    public boolean generateUpdateByExampleWithBLOBs() {
        return false;
    }

    public boolean generatePrimaryKeyClass() {
        return false;
    }

    public boolean generateBaseRecordClass() {
        return true;
    }

    public boolean generateRecordWithBLOBsClass() {
        return false;
    }

    public boolean generateJavaClient() {
        return false;
    }

    public TableInfo getIntrospectedTable() {
        return null;
    }

    public void setTableInfoWrapper(TableInfoWrapper tableInfoWrapper) {
        this.tableInfoWrapper = tableInfoWrapper;
    }
}

/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.config.rule;

import cn.vansky.code.generator.db.TableInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.JavaTypeInfo;

/**
 * 样例规则
 * Author: CK
 * Date: 2015/7/8.
 */
public class ExampleRules implements Rules {
    /** 封装表信息 */
    protected TableInfoWrapper tableInfoWrapper;

    public boolean generateInsert() {
        return true;
    }

    public boolean generateInsertBatch() {
        return false;
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
        return true;
    }

    public boolean generateBaseResultMap() {
        return true;
    }

    public boolean generateResultMapWithBLOBs() {
        return false;
    }

    public boolean generateSQLExampleWhereClause() {
        return true;
    }

    public boolean generateMyBatis3UpdateByExampleWhereClause() {
        return true;
    }

    public boolean generateBaseColumnList() {
        return true;
    }

    public boolean generateBlobColumnList() {
        return false;
    }

    public boolean generateBaseColumnEqual() {
        return false;
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
        return true;
    }

    public boolean generateSelectByExampleWithBLOBs() {
        return true;
    }

    public boolean generateExampleClass() {
        return true;
    }

    public boolean generateCountByExample() {
        return true;
    }

    public boolean generateCount() {
        return true;
    }

    public boolean generateUpdateByExampleSelective() {
        return true;
    }

    public boolean generateUpdateByExampleWithoutBLOBs() {
        return true;
    }

    public boolean generateUpdateByExampleWithBLOBs() {
        return true;
    }

    public boolean generatePrimaryKeyClass() {
        return false;
    }

    public boolean generateBaseRecordClass() {
        return false;
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

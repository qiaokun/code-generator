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

    @Override
    public boolean generateInsert() {
        return true;
    }

    @Override
    public boolean generateInsertBatch() {
        return false;
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
        return true;
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
        return true;
    }

    @Override
    public boolean generateMyBatis3UpdateByExampleWhereClause() {
        return true;
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
        return false;
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
        return true;
    }

    @Override
    public boolean generateSelectByExampleWithBLOBs() {
        return true;
    }

    @Override
    public boolean generateExampleClass() {
        return true;
    }

    @Override
    public boolean generateCountByExample() {
        return true;
    }

    @Override
    public boolean generateCount() {
        return true;
    }

    @Override
    public boolean generateUpdateByExampleSelective() {
        return true;
    }

    @Override
    public boolean generateUpdateByExampleWithoutBLOBs() {
        return true;
    }

    @Override
    public boolean generateUpdateByExampleWithBLOBs() {
        return true;
    }

    @Override
    public boolean generatePrimaryKeyClass() {
        return false;
    }

    @Override
    public boolean generateBaseRecordClass() {
        return false;
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

package cn.vansky.code.generator.db;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.java.JavaTypeInfo;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/20
 */
public abstract class AbstractAttributes {
    protected TableInfoWrapper tableInfoWrapper;
    protected TableInfo tableInfo;
    protected CodeGenContext context;

    /**
     * 计算JAVA信息
     */
    public abstract void calculateModelAttributes();

    /**
     * 计算XML信息
     */
    public abstract void calculateXmlAttributes();

    public abstract JavaTypeInfo getBo();

    public abstract String getBaseRecord();

    public abstract String getRecordWithBLOBsType();

    public void setTableInfoWrapper(TableInfoWrapper tableInfoWrapper) {
        this.tableInfoWrapper = tableInfoWrapper;
        this.tableInfo = tableInfoWrapper.getTableInfo();
        this.context = tableInfoWrapper.getContext();
    }

    /**
     * 计算生成*Mapper.xml文件名
     * @return UserMapper.xml
     */
    protected String calculateMyBatis3XmlMapperFileName() {
        return tableInfo.getDomainObjectName() + "Mapper.xml";
    }

    /**
     * 计算生成*Mapper.xml包名
     *
     * @return cn.vansky.framework.menu.user
     */
    protected String calculateSqlMapPackage() {
        return context.getTargetPackage() + "." + tableInfo.getDomainObjectSubPackage();
    }
    public static final String ATTR_BASE_RESULT_MAP_ID = "BaseResultMap";

    public static final String ATTR_RESULT_MAP_WITH_BLOBS_ID = "ResultMapWithBLOBs";

    public static final String ATTR_BASE_COLUMN_EQUAL_ID = "Base_Column_Equal";
    public static final String ATTR_BASE_COLUMN_LIKE_ID = "Base_Column_Like";
    public static final String ATTR_BASE_COLUMN_LIST_ID = "Base_Column_List";
    public static final String ATTR_BLOB_COLUMN_LIST_ID = "Blob_Column_List";
    public static final String ATTR_INSERT_STATEMENT_ID = "insert";
    public static final String ATTR_INSERT_BATCH_STATEMENT_ID = "insertBatch";
    public static final String ATTR_INSERT_SELECTIVE_STATEMENT_ID = "insertSelective";
    public static final String ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID = "deleteByPrimaryKey";
    public static final String ATTR_SELECT_ALl_BY_STATEMENT_ID = "selectAll";
    public static final String ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID = "selectByPrimaryKey";
    public static final String ATTR_COUNT_BY_STATEMENT_ID = "count";
    public static final String ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID = "updateByPrimaryKey";
    public static final String ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID = "updateByPrimaryKeySelective";
    public static final String ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID = "updateByPrimaryKeySelectiveWithBLOBs";
    // EXAMPLE
    public static final String ATTR_EXAMPLE_WHERE_CLAUSE_ID = "Example_Where_Clause";
    public static final String ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID = "Update_By_Example_Where_Clause";
    public static final String ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID = "deleteByExample";
    public static final String ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID = "selectByExample";
    public static final String ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID = "selectByExampleWithBLOBs";
    public static final String ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID = "countByExample";
    public static final String ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID = "updateByExample";
    public static final String ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID = "updateByExampleSelective";
    public static final String ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID = "updateByExampleWithBLOBs";

    public String getBaseResultMap() {
        return ATTR_BASE_RESULT_MAP_ID;
    }

    public String getBaseColumnEqual() {
        return ATTR_BASE_COLUMN_EQUAL_ID;
    }

    public String getBaseColumnList() {
        return ATTR_BASE_COLUMN_LIST_ID;
    }

    public String getBlobColumnList() {
        return ATTR_BLOB_COLUMN_LIST_ID;
    }

    public String getCount() {
        return ATTR_COUNT_BY_STATEMENT_ID;
    }

    public String getDeleteByPrimaryKey() {
        return ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID;
    }

    public String getInsertBatch() {
        return ATTR_INSERT_BATCH_STATEMENT_ID;
    }

    public String getInsert() {
        return ATTR_INSERT_STATEMENT_ID;
    }

    public String getInsertSelective() {
        return ATTR_INSERT_SELECTIVE_STATEMENT_ID;
    }

    public String getResultMapWithBLOBs() {
        return ATTR_RESULT_MAP_WITH_BLOBS_ID;
    }

    public String getSelectAll() {
        return ATTR_SELECT_ALl_BY_STATEMENT_ID;
    }

    public String getSelectByPrimaryKey() {
        return ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID;
    }

    public String getUpdateByPrimaryKey() {
        return ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID;
    }

    public String getUpdateByPrimaryKeySelectiveWithBLOBs() {
        return ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID;
    }

    public String getExampleWhereClause() {
        return ATTR_EXAMPLE_WHERE_CLAUSE_ID;
    }

    public String getUpdateByExampleWhereClause() {
        return ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID;
    }

    public abstract String getNamespace();
}

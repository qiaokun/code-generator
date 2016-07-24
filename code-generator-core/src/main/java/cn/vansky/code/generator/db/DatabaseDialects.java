/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.db;

/**
 * 数据库别名
 * Author: CK.
 * Date: 2015/6/6.
 */
public enum DatabaseDialects {

    /** The D b2. */
    DB2("DB2", "VALUES IDENTITY_VAL_LOCAL()"),
    /** The mysql. */
    MYSQL("MySQL", "SELECT LAST_INSERT_ID()"),
    /** The sqlserver. */
    SQLSERVER("SqlServer", "SELECT SCOPE_IDENTITY()"),
    /** The cloudscape. */
    CLOUDSCAPE("Cloudscape", "VALUES IDENTITY_VAL_LOCAL()"),
    /** The derby. */
    DERBY("Derby", "VALUES IDENTITY_VAL_LOCAL()"),
    /** The hsqldb. */
    HSQLDB("HSQLDB", "CALL IDENTITY()"),
    /** The sybase. */
    SYBASE("SYBASE", "SELECT @@IDENTITY"),
    /** The D b2_ mf. */
    DB2_MF("DB2_MF", "SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1"),
    /** The informix. */
    INFORMIX("Informix", "select dbinfo('sqlca.sqlerrd1') from systables where tabid=1");

    private String name;

    private String identityRetrievalStatement;

    private DatabaseDialects(String name, String identityRetrievalStatement) {
        this.name = name;
        this.identityRetrievalStatement = identityRetrievalStatement;
    }

    public String getName() {
        return name;
    }

    public String getIdentityRetrievalStatement() {
        return identityRetrievalStatement;
    }

    public static DatabaseDialects getDatabaseDialect(String database) {
        DatabaseDialects [] values = DatabaseDialects.values();
        for (DatabaseDialects dialects : values) {
            if (dialects.getName().equalsIgnoreCase(database)) {
                return dialects;
            }
        }
        return null;
    }
}

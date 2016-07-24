package cn.vansky.code.generator.db;

/**
 * SQL中分隔符
 * Author: CK
 * Date: 2015/9/5
 */
public enum DelimiterSQL {
    MySQL("MySQL", "`", "`"),
    DB2("DB2", "`", "`"),
    SqlServer("SqlServer", "`", "`"),
    Cloudscape("Cloudscape", "`", "`"),
    Derby("Derby", "`", "`"),
    HSQLDB("HSQLDB", "`", "`"),
    SYBASE("SYBASE", "`", "`"),
    DB2_MF("DB2_MF", "`", "`"),
    Informix("Informix", "`", "`");

    private String dataBaseName;
    /** 开始分隔符 */
    private String begin;
    /** 结束分隔符 */
    private String end;

    private DelimiterSQL(String dataBaseName, String begin, String end) {
        this.dataBaseName = dataBaseName;
        this.begin = begin;
        this.end = end;
    }

    public static DelimiterSQL getDatabaseDialect(String database) {
        DelimiterSQL [] values = DelimiterSQL.values();
        for (DelimiterSQL delimiterSQL : values) {
            if (delimiterSQL.getDataBaseName().equalsIgnoreCase(database)) {
                return delimiterSQL;
            }
        }
        return null;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }
}

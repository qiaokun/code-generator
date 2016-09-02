package cn.vansky.code.generator.api;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/9/1
 */
public enum TableInfoWrapperEnum {
    POS("POS"),
    PPMS("PPMS"),
    FRAME_WORK("FRAMEWORK"),
    ;

    private String name;

    private TableInfoWrapperEnum(String name) {
        this.name = name;
    }

    public static TableInfoWrapperEnum get(String name) {
        for (TableInfoWrapperEnum tableInfoWrapperEnum : TableInfoWrapperEnum.values()) {
            if (tableInfoWrapperEnum.name.equals(name)) {
                return tableInfoWrapperEnum;
            }
        }
        throw new RuntimeException("无自动生成代码的类型");
    }

    public String getName() {
        return name;
    }
}

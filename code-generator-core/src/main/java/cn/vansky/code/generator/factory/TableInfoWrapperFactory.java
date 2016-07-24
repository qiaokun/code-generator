package cn.vansky.code.generator.factory;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.framework.FrameWorkTableMyBatis3Impl;
import cn.vansky.code.generator.db.ppms.PPmsTableMyBatis3Impl;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class TableInfoWrapperFactory {
    private static TableInfoWrapperFactory instance = new TableInfoWrapperFactory();

    private TableInfoWrapperFactory() {

    }

    public static TableInfoWrapperFactory getInstance() {
        return instance;
    }

    public TableInfoWrapper getTableInfoWrapper(CodeGenContext context) {
        TableInfoWrapperEnum tableInfoWrapperEnum = context.getTableInfoWrapperEnum();
        if (TableInfoWrapperEnum.FRAME_WORK.equals(tableInfoWrapperEnum)) {
            return new FrameWorkTableMyBatis3Impl(context);
        } else if (TableInfoWrapperEnum.PPMS.equals(tableInfoWrapperEnum)) {
            return new PPmsTableMyBatis3Impl(context);
        }
        throw new RuntimeException("没有相应的TableInfoWrapper类: TableInfoWrapperFactory");
    }

    public static enum TableInfoWrapperEnum {
        FRAME_WORK("FRAME_WORK"),
        PPMS("PPMS"),
        ;

        private String name;

        private TableInfoWrapperEnum(String name) {
            this.name = name;
        }
    }
}

package cn.vansky.code.generator.factory;

import cn.vansky.code.generator.api.MyBatisGenerator;
import cn.vansky.code.generator.api.TableInfoWrapperEnum;
import cn.vansky.code.generator.api.pos.PosGenerator;
import cn.vansky.code.generator.api.ppms.PPmsGenerator;
import cn.vansky.code.generator.config.CodeGenContext;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/9/1
 */
public class GeneratorFactory {

    private static GeneratorFactory instance = new GeneratorFactory();

    private GeneratorFactory() {

    }

    public static GeneratorFactory getInstance() {
        return instance;
    }

    public MyBatisGenerator getTableInfoWrapper(CodeGenContext context) {
        TableInfoWrapperEnum tableInfoWrapperEnum = TableInfoWrapperEnum.get(context.getGeneratorType());
        if (TableInfoWrapperEnum.FRAME_WORK.equals(tableInfoWrapperEnum)) {
            return new MyBatisGenerator(context);
        } else if (TableInfoWrapperEnum.PPMS.equals(tableInfoWrapperEnum)) {
            return new PPmsGenerator(context);
        } else if (TableInfoWrapperEnum.POS.equals(tableInfoWrapperEnum)) {
            return new PosGenerator(context);
        }
        throw new RuntimeException("没有相应的TableInfoWrapper类: TableInfoWrapperFactory");
    }
}

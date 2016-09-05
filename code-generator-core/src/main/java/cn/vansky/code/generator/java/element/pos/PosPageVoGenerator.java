package cn.vansky.code.generator.java.element.pos;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.TopLevelClass;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/9/1
 */
public class PosPageVoGenerator extends AbstractJavaElementGenerator<PosAttributes> {

    public PosPageVoGenerator(TableInfoWrapper<PosAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    @Override
    public void prepareElement() {
        javaTypeInfo = attributes.getPageVo();
        superClass = new JavaTypeInfo("com.zrj.pay.core.model.PageObject");
    }

    @Override
    public void dealElement(TopLevelClass topLevelClass) {

    }
}

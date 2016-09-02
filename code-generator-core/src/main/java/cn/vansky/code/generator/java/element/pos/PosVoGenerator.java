package cn.vansky.code.generator.java.element.pos;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.TopLevelClass;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/9/1
 */
public class PosVoGenerator extends PosJavaElementGenerator {

    public PosVoGenerator(TableInfoWrapper tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    @Override
    public void prepareElement() {
        javaTypeInfo = attributes.getVo();
        superClass = new JavaTypeInfo("com.zrj.pay.core.model.BaseObject");
    }

    @Override
    public void dealElement(TopLevelClass topLevelClass) {

    }
}

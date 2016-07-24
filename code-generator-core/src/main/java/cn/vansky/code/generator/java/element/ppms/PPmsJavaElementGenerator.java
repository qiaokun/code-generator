package cn.vansky.code.generator.java.element.ppms;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/20
 */
public abstract class PPmsJavaElementGenerator extends AbstractJavaElementGenerator {

    public PPmsAttributes attributes;

    public PPmsJavaElementGenerator(TableInfoWrapper tableInfoWrapper) {
        super(tableInfoWrapper);
        this.attributes = (PPmsAttributes) tableInfoWrapper.getAttributes();
    }
}

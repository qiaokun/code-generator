package cn.vansky.code.generator.java.element.pos;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/20
 */
public abstract class PosJavaElementGenerator extends AbstractJavaElementGenerator {

    public PosAttributes attributes;

    public PosJavaElementGenerator(TableInfoWrapper tableInfoWrapper) {
        super(tableInfoWrapper);
        this.attributes = (PosAttributes) tableInfoWrapper.getAttributes();
    }
}

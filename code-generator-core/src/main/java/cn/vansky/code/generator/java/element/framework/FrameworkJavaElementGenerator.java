package cn.vansky.code.generator.java.element.framework;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/24
 */
public abstract class FrameworkJavaElementGenerator extends AbstractJavaElementGenerator {

    public FrameworkAttributes attributes;

    public FrameworkJavaElementGenerator(TableInfoWrapper tableInfoWrapper) {
        super(tableInfoWrapper);
        this.attributes = (FrameworkAttributes) tableInfoWrapper.getAttributes();
    }
}

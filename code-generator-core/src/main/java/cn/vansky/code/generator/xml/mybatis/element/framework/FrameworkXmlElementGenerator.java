package cn.vansky.code.generator.xml.mybatis.element.framework;

import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/24
 */
public abstract class FrameworkXmlElementGenerator extends AbstractXmlElementGenerator {
    public FrameworkAttributes attributes;

    @Override
    public void getAttributes() {
        this.attributes = (FrameworkAttributes) tableInfoWrapper.getAttributes();
    }
}

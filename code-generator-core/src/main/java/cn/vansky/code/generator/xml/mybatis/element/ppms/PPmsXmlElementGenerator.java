package cn.vansky.code.generator.xml.mybatis.element.ppms;

import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/24
 */
public abstract class PPmsXmlElementGenerator extends AbstractXmlElementGenerator {
    public PPmsAttributes attributes;

    @Override
    public void getAttributes() {
        this.attributes = (PPmsAttributes) tableInfoWrapper.getAttributes();
    }
}

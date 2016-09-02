package cn.vansky.code.generator.xml.mybatis.element.pos;

import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/24
 */
public abstract class PosXmlElementGenerator extends AbstractXmlElementGenerator {
    public PosAttributes attributes;

    @Override
    public void getAttributes() {
        this.attributes = (PosAttributes) tableInfoWrapper.getAttributes();
    }
}

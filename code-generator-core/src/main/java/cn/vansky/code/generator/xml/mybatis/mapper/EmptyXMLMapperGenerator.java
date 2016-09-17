package cn.vansky.code.generator.xml.mybatis.mapper;

import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.xml.XmlElement;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/16
 */
public class EmptyXMLMapperGenerator<T extends AbstractAttributes> extends AbstractXmlMapperGenerator<T> {

    public void getSqlMapElement(XmlElement answer) {
        answer.setEnd(false);
    }
}

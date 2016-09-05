package cn.vansky.code.generator.xml.mybatis.mapper;

import cn.vansky.code.generator.config.XmlConstants;
import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.XmlElement;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/16
 */
public class EmptyXMLMapperGenerator extends AbstractXmlMapperGenerator<AbstractAttributes> {

    public Document getDocument() {
        Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        document.setRootElement(getSqlMapElement());
        return document;
    }

    /**
     * 获取SQL MAP元素
     * @return XmlElement
     */
    protected XmlElement getSqlMapElement() {
        XmlElement answer = new XmlElement("mapper", false);
        String namespace = tableInfoWrapper.getAttributes().getNamespace();
        answer.addAttribute(new Attribute("namespace", namespace));
        return answer;
    }
}

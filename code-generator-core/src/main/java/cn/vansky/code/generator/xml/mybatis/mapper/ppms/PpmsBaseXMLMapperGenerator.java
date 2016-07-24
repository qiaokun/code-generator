package cn.vansky.code.generator.xml.mybatis.mapper.ppms;

import cn.vansky.code.generator.config.XmlConstants;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;
import cn.vansky.code.generator.xml.mybatis.element.ppms.*;
import cn.vansky.code.generator.xml.mybatis.mapper.AbstractXmlMapperGenerator;

/**
 * Base基础XML的MAPPER类
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PpmsBaseXMLMapperGenerator extends AbstractXmlMapperGenerator {

    @Override
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
        XmlElement answer = new XmlElement("mapper");
        String namespace = tableInfoWrapper.getAttributes().getNamespace();
        answer.addAttribute(new Attribute("namespace", namespace));

        addResultMapWithoutBLOBsElement(answer);

        addBaseColumnListElement(answer);

        addPpmsInsertElement(answer);

        addPpmsInsertBatchElement(answer);

        addPpmsUpdateElement(answer);

        addPpmsDeleteElement(answer);

        addPpmsGetElement(answer);

        addPpmsGetListElement(answer);

        addPpmsFindPageElement(answer);

        return answer;
    }

    protected void addPpmsInsertElement(XmlElement parentElement) {
        if (rules.generateInsertSelective()) {
            AbstractXmlElementGenerator elementGenerator = new PpmsInsertElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addPpmsUpdateElement(XmlElement parentElement) {
        if (rules.generateUpdateByPrimaryKeySelective()) {
            AbstractXmlElementGenerator elementGenerator = new PpmsUpdateElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addPpmsDeleteElement(XmlElement parentElement) {
        if (rules.generateDeleteByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new PpmsDeleteElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addPpmsGetElement(XmlElement parentElement) {
        if (rules.generateSelectByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new PpmsGetElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addPpmsGetListElement(XmlElement parentElement) {
        if (rules.generateSelectByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new PpmsGetListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addPpmsFindPageElement(XmlElement parentElement) {
        if (rules.generateSelectByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new PpmsFindPageElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addPpmsInsertBatchElement(XmlElement parentElement) {
        if (rules.generateInsertBatch()) {
            AbstractXmlElementGenerator elementGenerator = new PpmsInsertBatchElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
}

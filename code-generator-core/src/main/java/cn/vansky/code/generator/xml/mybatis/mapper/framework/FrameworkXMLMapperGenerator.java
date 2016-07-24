/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.mapper.framework;

import cn.vansky.code.generator.config.XmlConstants;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;
import cn.vansky.code.generator.xml.mybatis.element.framework.*;
import cn.vansky.code.generator.xml.mybatis.mapper.AbstractXmlMapperGenerator;

/**
 * Base基础XML的MAPPER类
 * Author: CK.
 * Date: 2015/6/6.
 */
public class FrameworkXMLMapperGenerator extends AbstractXmlMapperGenerator {
    
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

        // resultMap
        addResultMapWithoutBLOBsElement(answer);
        addResultMapWithBLOBsElement(answer);

        // Base_Column_List
        addBaseColumnListElement(answer);
        addBlobColumnListElement(answer);

        // insert
        addInsertElement(answer);
        // insertBatch
        addInsertBatchElement(answer);
        // insertSelective
        addInsertSelectiveElement(answer);

        // deleteByPrimaryKey
        addDeleteByPrimaryKeyElement(answer);

        // selectAll
        addSelectAllByElement(answer);
        // selectByPrimaryKey
        addSelectByPrimaryKeyElement(answer);

        // count
        addCountByElement(answer);

        // updateByPrimaryKeySelective
        addUpdateByPrimaryKeySelectiveElement(answer);
        // updateByPrimaryKey
        addUpdateByPrimaryKeyWithoutBLOBsElement(answer);
        addUpdateByPrimaryKeyWithBLOBsElement(answer);

        return answer;
    }

    /**
     * 主键查询
     * @param parentElement XmlElement
     */
    protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
        if (rules.generateSelectByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new SelectByPrimaryKeyElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectAllByElement(XmlElement parentElement) {
        if (rules.generateSelectAllByExample()) {
            AbstractXmlElementGenerator elementGenerator = new SelectAllByElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    /**
     * 主键删除
     * @param parentElement XmlElement
     */
    protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
        if (rules.generateDeleteByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    /**
     * 添加全部字段语句
     * @param parentElement XmlElement
     */
    protected void addInsertElement(XmlElement parentElement) {
        if (rules.generateInsert()) {
            AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    /**
     * 添加全部字段语句
     * @param parentElement XmlElement
     */
    protected void addInsertBatchElement(XmlElement parentElement) {
        if (rules.generateInsertBatch()) {
            AbstractXmlElementGenerator elementGenerator = new InsertBatchElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    /**
     * 添加部分字段
     * @param parentElement XmlElement
     */
    protected void addInsertSelectiveElement(XmlElement parentElement) {
        if (rules.generateInsertSelective()) {
            AbstractXmlElementGenerator elementGenerator = new InsertSelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addCountByElement(XmlElement parentElement) {
        if (rules.generateCount()) {
            AbstractXmlElementGenerator elementGenerator = new CountByElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    /**
     * 根据主键更新
     * @param parentElement XmlElement
     */
    protected void addUpdateByPrimaryKeySelectiveElement(XmlElement parentElement) {
        if (rules.generateUpdateByPrimaryKeySelective()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithBLOBsElement(XmlElement parentElement) {
        if (rules.generateUpdateByPrimaryKeyWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithoutBLOBsElement(XmlElement parentElement) {
        if (rules.generateUpdateByPrimaryKeyWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
}

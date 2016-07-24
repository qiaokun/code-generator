/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.mapper;

import cn.vansky.code.generator.api.AbstractGenerator;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;
import cn.vansky.code.generator.xml.mybatis.element.base.BaseColumnListElementGenerator;
import cn.vansky.code.generator.xml.mybatis.element.base.BlobColumnListElementGenerator;
import cn.vansky.code.generator.xml.mybatis.element.base.ResultMapWithBLOBsElementGenerator;
import cn.vansky.code.generator.xml.mybatis.element.base.ResultMapWithoutBLOBsElementGenerator;

/**
 * 公共的XML的MAPPER类
 * Author: CK.
 * Date: 2015/6/6.
 */
public abstract class AbstractXmlMapperGenerator extends AbstractGenerator {

    /**
     * 获取文档信息
     * @return 文档信息
     */
    public abstract Document getDocument();

    protected void initializeAndExecuteGenerator(AbstractXmlElementGenerator elementGenerator,
                                                 XmlElement parentElement) {
        elementGenerator.setTableInfoWrapper(tableInfoWrapper);
        elementGenerator.addElements(parentElement);
    }

    /**
     * resultMap
     * @param parentElement XmlElement
     */
    protected void addResultMapWithoutBLOBsElement(XmlElement parentElement) {
        if (rules.generateBaseResultMap()) {
            AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addResultMapWithBLOBsElement(XmlElement parentElement) {
        if (rules.generateResultMapWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new ResultMapWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    /**
     * 所有列基本信息
     * @param parentElement XmlElement
     */
    protected void addBaseColumnListElement(XmlElement parentElement) {
        if (rules.generateBaseColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addBlobColumnListElement(XmlElement parentElement) {
        if (rules.generateBlobColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new BlobColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
}

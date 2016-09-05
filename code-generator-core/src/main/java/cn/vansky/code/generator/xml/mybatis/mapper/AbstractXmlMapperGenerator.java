/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.mapper;

import cn.vansky.code.generator.api.AbstractGenerator;
import cn.vansky.code.generator.db.AbstractAttributes;
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
public abstract class AbstractXmlMapperGenerator<T extends AbstractAttributes> extends AbstractGenerator<T> {

    /**
     * 获取文档信息
     * @return 文档信息
     */
    public abstract Document getDocument();

    protected void initializeAndExecuteGenerator(AbstractXmlElementGenerator<T> elementGenerator,
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
            AbstractXmlElementGenerator<T> elementGenerator = new ResultMapWithoutBLOBsElementGenerator<T>(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addResultMapWithBLOBsElement(XmlElement parentElement) {
        if (rules.generateResultMapWithBLOBs()) {
            AbstractXmlElementGenerator<T> elementGenerator = new ResultMapWithBLOBsElementGenerator<T>();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    /**
     * 所有列基本信息
     * @param parentElement XmlElement
     */
    protected void addBaseColumnListElement(XmlElement parentElement) {
        if (rules.generateBaseColumnList()) {
            AbstractXmlElementGenerator<T> elementGenerator = new BaseColumnListElementGenerator<T>();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addBlobColumnListElement(XmlElement parentElement) {
        if (rules.generateBlobColumnList()) {
            AbstractXmlElementGenerator<T> elementGenerator = new BlobColumnListElementGenerator<T>();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
}

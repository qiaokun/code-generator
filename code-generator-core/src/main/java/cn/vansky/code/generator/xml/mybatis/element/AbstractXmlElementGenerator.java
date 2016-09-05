/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element;

import cn.vansky.code.generator.api.AbstractGenerator;
import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.XmlElement;
import org.apache.commons.lang.StringUtils;

/**
 * XML公共信息
 * Author: CK.
 * Date: 2015/6/6.
 */
public abstract class AbstractXmlElementGenerator<T extends AbstractAttributes> extends AbstractGenerator<T> {

    public T attributes;

    protected XmlElement answer;

    protected String name;

    protected String id;

    protected String parameterType;

    protected String resultType;

    protected String resultMap;

    protected String type;

    /**
     * 添加元素信息
     * @param parentElement 元素
     */
    public void addElements(XmlElement parentElement) {
        this.attributes = tableInfoWrapper.getAttributes();
        prepareXmlElement();
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("name is NULL");
        }
        answer = new XmlElement(name);
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("id is NULL");
        }
        answer.addAttribute(new Attribute("id", id));
        if (StringUtils.isNotBlank(parameterType)) {
            answer.addAttribute(new Attribute("parameterType", parameterType));
        }

        if (StringUtils.isNotBlank(resultMap)) {
            answer.addAttribute(new Attribute("resultMap", resultMap));
        } else if (StringUtils.isNotBlank(resultType)){
            answer.addAttribute(new Attribute("resultType", resultType));
        }

        if (StringUtils.isNotBlank(type)) {
            answer.addAttribute(new Attribute("type", type));
        }

//      commentGenerator.addComment(answer);

        dealElements();

        parentElement.addElement(answer);
    }

    /**
     * 预处理，计算name、id、parameterType、resultType、resultMap、
     */
    public abstract void prepareXmlElement();

    /**
     * 处理具体内容
     */
    public abstract void dealElements();

    /**
     * 基础列信息
     * @return XmlElement
     */
    protected XmlElement getBaseColumnListElement() {
        XmlElement answer = new XmlElement("include");
        answer.addAttribute(new Attribute("refid", tableInfoWrapper.getAttributes().getBaseColumnList()));
        return answer;
    }

    /**
     * Blob列信息
     * @return
     */
    protected XmlElement getBlobColumnListElement() {
        XmlElement answer = new XmlElement("include");
        answer.addAttribute(new Attribute("refid", tableInfoWrapper.getAttributes().getBlobColumnList()));
        return answer;
    }

    /**
     *
     * @return XmlElement
     */
    protected XmlElement getBaseColumnEqualElement() {
        XmlElement answer = new XmlElement("include");
        answer.addAttribute(new Attribute("refid", tableInfoWrapper.getAttributes().getBaseColumnEqual()));
        return answer;
    }

    protected XmlElement getExampleIncludeElement() {
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "_parameter != null"));

        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", tableInfoWrapper.getAttributes().getExampleWhereClause()));
        ifElement.addElement(includeElement);

        return ifElement;
    }

    protected XmlElement getUpdateByExampleIncludeElement() {
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "_parameter != null"));

        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", tableInfoWrapper.getAttributes().getUpdateByExampleWhereClause()));
        ifElement.addElement(includeElement);

        return ifElement;
    }
}

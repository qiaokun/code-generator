/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.spring.element;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.config.SpringXMLConstants;
import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.XmlElement;

import java.util.List;

/**
 * 公共的XML的MAPPER类
 * Author: CK.
 * Date: 2015/6/6.
 */
public abstract class AbstractSpringXMLElement<T extends AbstractAttributes> {

    protected String name;

    protected CodeGenContext context;

    protected List<TableInfoWrapper<T>> tableInfoWrappers;

    protected AbstractSpringXMLElement(String name, CodeGenContext context, List<TableInfoWrapper<T>> tableInfoWrappers) {
        this.name = name;
        this.context = context;
        this.tableInfoWrappers = tableInfoWrappers;
    }

    public Document getDocument() {
        Document document = new Document(name);
        XmlElement answer = new XmlElement("beans");
        answer.addAttribute(new Attribute(SpringXMLConstants.XMLNS, SpringXMLConstants.SPRING_BEANS));
        answer.addAttribute(new Attribute("\n\t\t\t\t" + SpringXMLConstants.XMLNS_XSI, SpringXMLConstants.SPRING_XML_SCHEMA_INSTANCE));
        getElement(answer);
        document.setRootElement(answer);
        return document;
    }

    public abstract void getElement(XmlElement answer);
}

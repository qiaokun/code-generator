/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element.framework;

import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/7
 */
public class SelectAllByElementGenerator extends AbstractXmlElementGenerator<FrameworkAttributes> {

    public void prepareXmlElement() {
        name = "select";
        id = attributes.getSelectAll();
        if (rules.generateResultMapWithBLOBs()) {
            resultMap = attributes.getResultMapWithBLOBs();
        } else {
            resultMap = attributes.getBaseResultMap();
        }
    }

    public void dealElements() {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement());
        if (tableInfoWrapper.hasBLOBColumns()) {
            answer.addElement(new TextElement(","));
            answer.addElement(getBlobColumnListElement());
        }

        sb.setLength(0);
        sb.append("from ");
        sb.append(tableInfoWrapper.getTableInfo().getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
    }
}

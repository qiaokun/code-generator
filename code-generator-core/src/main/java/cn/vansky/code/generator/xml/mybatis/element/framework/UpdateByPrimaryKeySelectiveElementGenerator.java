/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element.framework;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.util.MyBatis3FormattingUtil;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * 生成更新语句（通过主键更新）
 * Author: CK
 * Date: 2015/6/7
 */
public class UpdateByPrimaryKeySelectiveElementGenerator extends AbstractXmlElementGenerator<FrameworkAttributes> {

    public void prepareXmlElement() {
        name = "update";
        id = attributes.getUpdateByPrimaryKeySelective();
        String tmpParameterType;

        if (rules.generateRecordWithBLOBsClass()) {
            tmpParameterType = attributes.getRecordWithBLOBsType();
        } else {
            tmpParameterType = attributes.getBo().getFullyQualifiedName();
        }
        parameterType = tmpParameterType;
    }

    public void dealElements() {
        StringBuilder sb = new StringBuilder();

        sb.append("update ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement dynamicElement = new XmlElement("set");
        answer.addElement(dynamicElement);

        for (ColumnInfo c : tableInfoWrapper.getNonPrimaryKeyColumns()) {
            XmlElement isNotNullElement = new XmlElement("if");
            sb.setLength(0);
            sb.append(c.getJavaProperty());
            sb.append(" != null");
            isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            dynamicElement.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(MyBatis3FormattingUtil.getEscapedColumnName(c));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtil.getParameterClause(c));
            sb.append(',');

            isNotNullElement.addElement(new TextElement(sb.toString()));
        }

        boolean and = false;
        for (ColumnInfo c : tableInfoWrapper.getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                sb.append("  and ");
            } else {
                sb.append("where ");
                and = true;
            }

            sb.append(MyBatis3FormattingUtil.getEscapedColumnName(c));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtil.getParameterClause(c));
            answer.addElement(new TextElement(sb.toString()));
        }
    }
}

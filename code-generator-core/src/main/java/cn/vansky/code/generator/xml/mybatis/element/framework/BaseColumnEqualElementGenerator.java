/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
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
 * Where精确匹配字段
 * Author: CK
 * Date: 2015/7/9.
 */
public class BaseColumnEqualElementGenerator extends AbstractXmlElementGenerator<FrameworkAttributes> {

    public void prepareXmlElement() {
        name = "sql";
        id = attributes.getBaseColumnEqual();
    }

    public void dealElements() {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo c : tableInfoWrapper.getAllColumns()) {
            XmlElement isNotNullElement = new XmlElement("if");
            sb.setLength(0);
            sb.append(c.getJavaProperty());
            sb.append(" != null");
            isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            answer.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append("AND ");
            sb.append(MyBatis3FormattingUtil.getEscapedColumnName(c));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtil.getParameterClause(c));

            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
    }
}

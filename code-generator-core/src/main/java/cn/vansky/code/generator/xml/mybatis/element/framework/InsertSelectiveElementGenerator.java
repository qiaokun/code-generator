/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element.framework;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.util.MyBatis3FormattingUtil;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.XmlElement;

/**
 * 添加部分字段,if-test
 * Author: CK
 * Date: 2015/6/7
 */
public class InsertSelectiveElementGenerator extends FrameworkXmlElementGenerator {

    private String useGeneratedKeys = "true";

    private String keyProperty;

    @Override
    public void prepareXmlElement() {
        name = "insert";
        id = attributes.getInsertSelective();
        parameterType = attributes.getBo().getFullyQualifiedName();
    }

    @Override
    public void dealElements() {
        if (tableInfoWrapper.getPrimaryKeyColumns().size() > 1) {
            keyProperty = "hashmap";
        } else {
            keyProperty = tableInfoWrapper.getPrimaryKeyColumns().get(0).getActualColumnName();
        }
        answer.addAttribute(new Attribute("useGeneratedKeys", useGeneratedKeys));
        answer.addAttribute(new Attribute("keyProperty", keyProperty));

        StringBuilder sb = new StringBuilder();

        sb.append("insert into ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement insertTrimElement = new XmlElement("trim");
        insertTrimElement.addAttribute(new Attribute("prefix", "("));
        insertTrimElement.addAttribute(new Attribute("suffix", ")"));
        insertTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
        answer.addElement(insertTrimElement);

        XmlElement valuesTrimElement = new XmlElement("trim");
        valuesTrimElement.addAttribute(new Attribute("prefix", "values ("));
        valuesTrimElement.addAttribute(new Attribute("suffix", ")"));
        valuesTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
        answer.addElement(valuesTrimElement);

        for (ColumnInfo c : tableInfoWrapper.getAllColumns()) {
            // 自增不设值
            if (c.isIdentity()) {
                continue;
            }

            if (c.isSequenceColumn() || c.getJavaTypeInfo().isPrimitive()) {
                // 序列字段必须的
                // MyBatis3是在查询前先解析sql语句
                sb.setLength(0);
                sb.append(MyBatis3FormattingUtil.getEscapedColumnName(c));
                sb.append(',');
                insertTrimElement.addElement(new TextElement(sb.toString()));

                sb.setLength(0);
                sb.append(MyBatis3FormattingUtil.getParameterClause(c));
                sb.append(',');
                valuesTrimElement.addElement(new TextElement(sb.toString()));

                continue;
            }

            XmlElement insertNotNullElement = new XmlElement("if");
            sb.setLength(0);
            sb.append(c.getJavaProperty());
            sb.append(" != null");
            insertNotNullElement.addAttribute(new Attribute("test", sb.toString()));

            sb.setLength(0);
            sb.append(MyBatis3FormattingUtil.getEscapedColumnName(c));
            sb.append(',');
            insertNotNullElement.addElement(new TextElement(sb.toString()));
            insertTrimElement.addElement(insertNotNullElement);

            XmlElement valuesNotNullElement = new XmlElement("if");
            sb.setLength(0);
            sb.append(c.getJavaProperty());
            sb.append(" != null");
            valuesNotNullElement.addAttribute(new Attribute("test", sb.toString()));

            sb.setLength(0);
            sb.append(MyBatis3FormattingUtil.getParameterClause(c));
            sb.append(',');
            valuesNotNullElement.addElement(new TextElement(sb.toString()));
            valuesTrimElement.addElement(valuesNotNullElement);
        }
    }
}

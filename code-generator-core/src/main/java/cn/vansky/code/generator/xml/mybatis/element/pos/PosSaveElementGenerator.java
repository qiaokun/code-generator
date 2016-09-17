/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element.pos;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.util.MyBatis3FormattingUtil;
import cn.vansky.code.generator.util.OutputUtil;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加全部字段语句
 * Author: CK
 * Date: 2015/6/7
 */
public class PosSaveElementGenerator extends AbstractXmlElementGenerator<PosAttributes> {

    public void prepareXmlElement() {
        name = "insert";
        id = "save";
        parameterType = attributes.getBo().getFullyQualifiedName();
    }

    public void dealElements() {
        String keyProperty;
        if (tableInfoWrapper.getPrimaryKeyColumns().size() > 1) {
            keyProperty = "hashmap";
        } else {
            keyProperty = tableInfoWrapper.getPrimaryKeyColumns().get(0).getActualColumnName();
        }
        answer.addAttribute(new Attribute("useGeneratedKeys", "true"));
        answer.addAttribute(new Attribute("keyProperty", keyProperty));
        answer.addElement(new TextElement("<![CDATA["));

        StringBuilder insertClause = new StringBuilder();
        StringBuilder valuesClause = new StringBuilder();

        insertClause.append("insert into ");
        insertClause.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" (");

        valuesClause.append("values (");

        List<String> valuesClauses = new ArrayList<String>();
        List<ColumnInfo> columns = tableInfoWrapper.getAllColumns();
        for (int i = 0; i < columns.size(); i++) {
            ColumnInfo column = columns.get(i);
            if (column.isIdentity()) {
                // 自增不设值
                continue;
            }

            insertClause.append(MyBatis3FormattingUtil.getEscapedColumnName(column));
            valuesClause.append(MyBatis3FormattingUtil.getParameterClause(column));
            if (i + 1 < columns.size()) {
                if (!columns.get(i + 1).isIdentity()) {
                    insertClause.append(", ");
                    valuesClause.append(", ");
                }
            }

            if (valuesClause.length() > 80) {
                answer.addElement(new TextElement(insertClause.toString()));
                insertClause.setLength(0);
                OutputUtil.xmlIndent(insertClause, 1);

                valuesClauses.add(valuesClause.toString());
                valuesClause.setLength(0);
                OutputUtil.xmlIndent(valuesClause, 1);
            }
        }

        insertClause.append(')');
        answer.addElement(new TextElement(insertClause.toString()));

        valuesClause.append(')');
        valuesClauses.add(valuesClause.toString());

        for (String clause : valuesClauses) {
            answer.addElement(new TextElement(clause));
        }
        answer.addElement(new TextElement("]]>"));
    }
}

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element.pos;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.util.MyBatis3FormattingUtil;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * 主键查询
 * Author: CK
 * Date: 2015/6/7
 */
public class PosSelectByPrimaryKeyElementGenerator extends AbstractXmlElementGenerator<PosAttributes> {

    public void prepareXmlElement() {
        name = "select";
        id = attributes.getSelectByPrimaryKey();
        resultMap = attributes.getBaseResultMap();
        String tmpParameterType;
        // 多主键，就是map
        if (tableInfoWrapper.getPrimaryKeyColumns().size() > 1) {
            tmpParameterType = "hashmap";
        } else {
            tmpParameterType = tableInfoWrapper.getPrimaryKeyColumns().get(0).getJavaTypeInfo().toString();
        }
        parameterType = tmpParameterType;
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

        boolean and = false;
        for (ColumnInfo c : tableInfoWrapper.getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                sb.append("  and ");
            } else {
                sb.append("where ");
                and = true;
            }

            sb.append(MyBatis3FormattingUtil.getAliasedEscapedColumnName(c));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtil.getParameterClause(c));
            answer.addElement(new TextElement(sb.toString()));
        }
    }
}

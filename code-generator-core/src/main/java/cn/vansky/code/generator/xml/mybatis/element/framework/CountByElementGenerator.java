/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element.framework;

import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * 查询总数量
 * Author: CK
 * Date: 2015/6/7
 */
public class CountByElementGenerator extends AbstractXmlElementGenerator<FrameworkAttributes> {

    public void prepareXmlElement() {
        name = "select";
        id = attributes.getCount();
        resultType = "java.lang.Integer";
    }

    public void dealElements() {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from ");
        sb.append(tableInfoWrapper.getTableInfo().getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
    }
}

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element.base;

import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.util.MyBatis3FormattingUtil;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

import java.util.Iterator;

/**
 * 基础列元素
 * Author: CK.
 * Date: 2015/6/6.
 */
public class BaseColumnListElementGenerator<T extends AbstractAttributes> extends AbstractXmlElementGenerator<T> {

    public void prepareXmlElement() {
        name = "sql";
        id = attributes.getBaseColumnList();
    }

    public void dealElements() {
        StringBuilder sb = new StringBuilder();
        Iterator<ColumnInfo> iter = tableInfoWrapper.getNonBLOBColumns().iterator();
        while (iter.hasNext()) {
            sb.append(MyBatis3FormattingUtil.getSelectListPhrase(iter.next()));

            if (iter.hasNext()) {
                sb.append(", ");
            }

            if (sb.length() > 120) {
                answer.addElement(new TextElement(sb.toString()));
                sb.setLength(0);
            }
        }

        if (sb.length() > 0) {
            answer.addElement((new TextElement(sb.toString())));
        }
    }
}

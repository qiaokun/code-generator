/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.formatter;

import cn.vansky.code.generator.xml.Document;

/**
 * XML文件格式化接口（默认）
 * Author: CK.
 * Date: 2015/6/6.
 */
public class DefaultXmlFormatter implements XmlFormatter {

    @Override
    public String getFormattedContent(Document document) {
        return document.getFormattedContent(0);
    }
}

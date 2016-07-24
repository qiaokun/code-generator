/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.formatter;

import cn.vansky.code.generator.xml.Document;

/**
 * XML文件格式化接口
 * Author: CK.
 * Date: 2015/6/6.
 */
public interface XmlFormatter {

    /**
     * 生成XML内容
     * @param document 目标文档
     * @return XML内容
     */
    String getFormattedContent(Document document);
}

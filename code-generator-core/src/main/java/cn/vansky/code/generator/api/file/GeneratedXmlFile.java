/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.file;

import cn.vansky.code.generator.api.GeneratedFile;
import cn.vansky.code.generator.api.formatter.XmlFormatter;
import cn.vansky.code.generator.xml.Document;

/**
 * XML文件
 * Author: CK.
 * Date: 2015/6/6.
 */
public class GeneratedXmlFile extends GeneratedFile {

    /** 生成文档信息 */
    private Document document;

    /** 生成Mapper.xml文件名 */
    private String fileName;

    /** 生成文件包名 */
    private String targetPackage;

    /** XML格式化接口 */
    private XmlFormatter xmlFormatter;

    public GeneratedXmlFile(Document document, String fileName,
                            String targetPackage, String targetProject,
                            XmlFormatter xmlFormatter) {
        super(targetProject);
        this.document = document;
        this.fileName = fileName;
        this.targetPackage = targetPackage;
        this.xmlFormatter = xmlFormatter;
    }

    @Override
    public String getFormattedContent() {
        return xmlFormatter.getFormattedContent(document);
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getTargetPackage() {
        return targetPackage;
    }
}

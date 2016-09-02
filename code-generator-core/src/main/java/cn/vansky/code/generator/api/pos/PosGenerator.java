/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.pos;

import cn.vansky.code.generator.api.MyBatisGenerator;
import cn.vansky.code.generator.api.file.GeneratedJavaFile;
import cn.vansky.code.generator.api.file.GeneratedXmlFile;
import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.spring.document.AbstractSpringXMLDocument;
import cn.vansky.code.generator.xml.spring.document.pos.PosSpringXMLDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * PPMS 扩展添加spring xml
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PosGenerator extends MyBatisGenerator {

    protected AbstractSpringXMLDocument springXmlMapperGenerator;

    public PosGenerator(CodeGenContext context) {
        super(context);
        this.context = context;
        this.generatedJavaFiles = new ArrayList<GeneratedJavaFile>();
        this.generatedXmlFiles = new ArrayList<GeneratedXmlFile>();
    }

    /**
     * 自动生成文件
     */
    public void generateFiles() {
        super.generateFiles();
        this.springXmlMapperGenerator = new PosSpringXMLDocument(context, tableInfoWrappers);
        List<Document> springDocument = springXmlMapperGenerator.getDocument();
        for (Document d : springDocument) {
            GeneratedXmlFile gxf1 = new GeneratedXmlFile(d, d.getName(),
                    "", context.getTargetPackage(), context.getXmlFormatter());
            generatedXmlFiles.add(gxf1);
        }
    }
}

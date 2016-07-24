/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.ppms;

import cn.vansky.code.generator.api.MyBatisGenerator;
import cn.vansky.code.generator.api.file.GeneratedJavaFile;
import cn.vansky.code.generator.api.file.GeneratedXmlFile;
import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.factory.TableInfoWrapperFactory;
import cn.vansky.code.generator.xml.spring.document.AbstractSpringXMLDocument;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.spring.document.ppms.PPmsSpringXMLDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * PPMS 扩展添加spring xml
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PPmsGenerator extends MyBatisGenerator {

    protected AbstractSpringXMLDocument springXmlMapperGenerator;

    public PPmsGenerator(CodeGenContext context) {
        super(context);
        this.context = context;
        this.context.setTableInfoWrapperEnum(TableInfoWrapperFactory.TableInfoWrapperEnum.PPMS);
        this.generatedJavaFiles = new ArrayList<GeneratedJavaFile>();
        this.generatedXmlFiles = new ArrayList<GeneratedXmlFile>();
    }

    /**
     * 自动生成文件
     */
    public void generateFiles() {
        super.generateFiles();
        this.springXmlMapperGenerator = new PPmsSpringXMLDocument(context, tableInfoWrappers);
        List<Document> springDocument = springXmlMapperGenerator.getDocument();
        for (Document d : springDocument) {
            GeneratedXmlFile gxf1 = new GeneratedXmlFile(d, d.getName(),
                    "", context.getTargetPackage(), context.getXmlFormatter());
            generatedXmlFiles.add(gxf1);
        }
    }
}

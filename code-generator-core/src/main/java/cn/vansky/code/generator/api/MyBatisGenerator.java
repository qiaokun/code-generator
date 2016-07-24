/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api;

import cn.vansky.code.generator.api.file.GeneratedJavaFile;
import cn.vansky.code.generator.api.file.GeneratedXmlFile;
import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.DatabaseWrapper;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.framework.common.util.FileUtilies;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MyBatis XML及java类
 * Author: CK.
 * Date: 2015/6/6.
 */
public class MyBatisGenerator {

    protected CodeGenContext context;

    /** JAVA文件列表 */
    protected List<GeneratedJavaFile> generatedJavaFiles;

    /** XML文件列表 */
    protected List<GeneratedXmlFile> generatedXmlFiles;

    protected List<TableInfoWrapper> tableInfoWrappers;

    public MyBatisGenerator(CodeGenContext context) {
        this.context = context;
        this.generatedJavaFiles = new ArrayList<GeneratedJavaFile>();
        this.generatedXmlFiles = new ArrayList<GeneratedXmlFile>();
    }

    /**
     * 自动生成文件
     */
    public void generate() {
        this.generateFiles();
        this.createDir();
        this.write();
    }

    /**
     * 自动生成文件
     */
    protected void generateFiles() {
        DatabaseWrapper databaseWrapper = context.getDatabaseWrapper();
        databaseWrapper.createTableInfoWrapper();
        tableInfoWrappers = databaseWrapper.getTableInfoWrappers();
        if (null != tableInfoWrappers) {
            for (TableInfoWrapper tableInfoWrapper : tableInfoWrappers) {
                tableInfoWrapper.initialize();
                generatedJavaFiles.addAll(tableInfoWrapper.getGeneratedJavaFiles());
                generatedXmlFiles.addAll(tableInfoWrapper.getGeneratedXmlFiles());
            }
        }
    }

    /**
     * 写文件
     */
    protected void write() {
        for (GeneratedJavaFile gxf : generatedJavaFiles) {
            // 目标文件
            File targetFile = FileUtilies.getDirectory("main." + gxf.getTargetPackage(), gxf.getFileName(), context.getOut());
            // 目标内容
            String source = gxf.getFormattedContent();
            // 输出流写文件
            FileUtilies.writeFile(targetFile, source, "UTF-8");
        }

        for (GeneratedXmlFile gxf : generatedXmlFiles) {
            // 目标文件
            File targetFile = FileUtilies.getDirectory("resources." + gxf.getTargetPackage(), gxf.getFileName(), context.getOut());
            // 目标内容
            String source = gxf.getFormattedContent();
            // 输出流写文件
            FileUtilies.writeFile(targetFile, source, "UTF-8");
        }
    }

    /**
     * 创建生成文件夹
     */
    protected void createDir() {
        String out = this.getClass().getResource("/").getPath() + new Date().getTime() + File.separator;
        context.setOut(out);
        File file = new File(context.getOut());
        try {
            FileUtils.forceMkdir(file);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        context.setFile(file);
    }
}

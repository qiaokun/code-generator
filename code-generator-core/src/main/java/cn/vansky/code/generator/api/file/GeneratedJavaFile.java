/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.file;

import cn.vansky.code.generator.api.GeneratedFile;
import cn.vansky.code.generator.api.formatter.JavaFormatter;
import cn.vansky.code.generator.java.CompilationUnit;

/**
 * JAVA自动生成文件类
 * Author: CK.
 * Date: 2015/6/6.
 */
public class GeneratedJavaFile extends GeneratedFile {

    /** JAVA类接口 */
    private CompilationUnit compilationUnit;

    /** 文件编码 */
    private String fileEncoding;

    /** JAVA格式 */
    private JavaFormatter javaFormatter;

    public GeneratedJavaFile(CompilationUnit compilationUnit,
                             String targetProject,
                             String fileEncoding,
                             JavaFormatter javaFormatter) {
        super(targetProject);
        this.compilationUnit = compilationUnit;
        this.fileEncoding = fileEncoding;
        this.javaFormatter = javaFormatter;
    }
    public String getFormattedContent() {
        return javaFormatter.getFormattedContent(compilationUnit);
    }

    public String getFileName() {
        return compilationUnit.getType().getShortName() + ".java";
    }

    public String getTargetPackage() {
        return compilationUnit.getType().getPackageName();
    }
}

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.formatter;

import cn.vansky.code.generator.java.CompilationUnit;

/**
 * JAVA文件格式化接口（默认）
 * Author: CK
 * Date: 2015/6/13
 */
public class DefaultJavaFormatter implements JavaFormatter {

    public String getFormattedContent(CompilationUnit compilationUnit) {
        return compilationUnit.getFormattedContent();
    }
}

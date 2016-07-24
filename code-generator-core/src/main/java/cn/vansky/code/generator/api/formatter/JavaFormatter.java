/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.formatter;

import cn.vansky.code.generator.java.CompilationUnit;

/**
 * JAVA文件格式化接口
 * Author: CK.
 * Date: 2015/6/6.
 */
public interface JavaFormatter {
    /**
     * 生成JAVA内容
     * @param compilationUnit
     * @return JAVA内容
     */
    String getFormattedContent(CompilationUnit compilationUnit);
}

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api;

/**
 * 通用的自动生成文件类
 * Author: CK.
 * Date: 2015/6/6.
 */
public abstract class GeneratedFile {

    /** 目标项目名 */
    protected String targetProject;

    public GeneratedFile(String targetProject) {
        this.targetProject = targetProject;
    }

    /**
     * 获取最终生成内容
     *
     * @return 内容
     */
    public abstract String getFormattedContent();

    /**
     * 获取文件名
     *
     * @return 文件名
     */
    public abstract String getFileName();

    /**
     * 获取目标项目名
     *
     * @return 目标项目名
     */
    public String getTargetProject() {
        return targetProject;
    }

    /**
     * 获取目标包名
     *
     * @return 目标包名
     */
    public abstract String getTargetPackage();

    public String toString() {
        return getFormattedContent();
    }
}

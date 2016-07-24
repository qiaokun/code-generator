/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml;

/**
 * element interface
 * Author: CK.
 * Date: 2015/6/6.
 */
public interface Element {
    /**
     * element content
     * @param indentLevel the level is control out format, like <b>Tab</b>
     * @return the result String
     */
    public abstract String getFormattedContent(int indentLevel);
}

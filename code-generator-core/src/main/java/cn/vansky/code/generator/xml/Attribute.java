/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml;

/**
 * the XML of attribute. the format is <code>name</code>="<code>value</code>"
 * <p><code>Element attribute = new Attribute("id", "getList");</code></p>
 * <p><code>attribute.getFormattedContent(1);</code></p>
 * <p>the result is <b>id="getList"</b></p>
 * Author: CK.
 * Date: 2015/6/5.
 */
public class Attribute implements Element {
    private String name;

    private String value;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("=\"");
        sb.append(value);
        sb.append('\"');
        return sb.toString();
    }
}

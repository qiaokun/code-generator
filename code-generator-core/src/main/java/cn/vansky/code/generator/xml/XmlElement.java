/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml;

import cn.vansky.code.generator.util.OutputUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * the XML of XML element.
 * Author: CK.
 * Date: 2015/6/6.
 */
public class XmlElement implements Element {

    private List<Attribute> attributes;

    private List<Element> elements;

    /** 每个节点名称 */
    private String name;

    /** 是否包含结束标签,true:不需要,false:需要 */
    private boolean end;

    public XmlElement(String name) {
        this(name, true);
    }

    public XmlElement(String name, boolean end) {
        attributes = new ArrayList<Attribute>();
        elements = new ArrayList<Element>();
        this.name = name;
        this.end = end;
    }

    public XmlElement(XmlElement original) {
        attributes = new ArrayList<Attribute>();
        attributes.addAll(original.attributes);
        elements = new ArrayList<Element>();
        elements.addAll(original.elements);
        this.name = original.name;
    }

    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        // 生成名称对应标签
        OutputUtil.xmlIndent(sb, indentLevel);
        sb.append('<');
        sb.append(this.name);

        // 放入标签属性
        for (Attribute att : this.attributes) {
            // 属性间一个空格
            sb.append(' ');
            sb.append(att.getFormattedContent(0));
        }

        if (this.elements.size() > 0) {
            sb.append(">");
            for (Element element : this.elements) {
                OutputUtil.newLine(sb);
                sb.append(element.getFormattedContent(indentLevel + 1));
            }
            // 生成名称对应的结束标签
            OutputUtil.newLine(sb);
            OutputUtil.xmlIndent(sb, indentLevel);
            sb.append("</");
            sb.append(this.name);
            sb.append('>');
        } else {
            if (end) {
                sb.append(" />");
            } else {
                sb.append('>');
                OutputUtil.newLine(sb);
                OutputUtil.newLine(sb);
                sb.append("</");
                sb.append(this.name);
                sb.append('>');
            }
        }

        return sb.toString();
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public String getName() {
        return name;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}

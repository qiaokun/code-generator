/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.xml.mybatis.element.base;

import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.util.MyBatis3FormattingUtil;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 基础resultMap（非BLOB字段）
 * Author: CK
 * Date: 2015/6/7
 */
public class ResultMapWithoutBLOBsElementGenerator extends AbstractXmlElementGenerator {

    protected AbstractAttributes attributes;

    private boolean isSimple;

    public ResultMapWithoutBLOBsElementGenerator(boolean isSimple) {
        super();
        this.isSimple = isSimple;
    }

    @Override
    public void getAttributes() {
        this.attributes = tableInfoWrapper.getAttributes();
    }

    @Override
    public void prepareXmlElement() {
        name = "resultMap";
        id = attributes.getBaseResultMap();
        String returnType;
        if (isSimple) {
            returnType = attributes.getBo().getFullyQualifiedName();
        } else {
            returnType = attributes.getBo().getFullyQualifiedName();
        }
        type = returnType;
    }

    @Override
    public void dealElements() {
        if (tableInfoWrapper.isConstructorBased()) {
            addResultMapConstructorElements(answer);
        } else {
            addResultMapElements(answer);
        }
    }

    private void addResultMapElements(XmlElement answer) {
        for (ColumnInfo introspectedColumn : tableInfoWrapper.getPrimaryKeyColumns()) {
            XmlElement resultElement = new XmlElement("id");

            resultElement.addAttribute(new Attribute(
                    "column", MyBatis3FormattingUtil.getRenamedColumnNameForResultMap(introspectedColumn)));
            resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));
            resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));

            if (StringUtils.isNotBlank(introspectedColumn.getTypeHandler())) {
                resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
            }

            answer.addElement(resultElement);
        }

        List<ColumnInfo> columns;
        if (isSimple) {
            columns = tableInfoWrapper.getNonPrimaryKeyColumns();
        } else {
            columns = tableInfoWrapper.getBaseColumns();
        }
        for (ColumnInfo introspectedColumn : columns) {
            XmlElement resultElement = new XmlElement("result");

            resultElement.addAttribute(new Attribute(
                    "column", MyBatis3FormattingUtil.getRenamedColumnNameForResultMap(introspectedColumn)));
            resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));
            resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));

            if (StringUtils.isNotBlank(introspectedColumn.getTypeHandler())) {
                resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
            }

            answer.addElement(resultElement);
        }
    }

    private void addResultMapConstructorElements(XmlElement answer) {
        XmlElement constructor = new XmlElement("constructor");

        for (ColumnInfo c : tableInfoWrapper.getPrimaryKeyColumns()) {
            XmlElement resultElement = new XmlElement("idArg");
            resultElement.addAttribute(new Attribute(
                    "column", MyBatis3FormattingUtil.getRenamedColumnNameForResultMap(c)));
            resultElement.addAttribute(new Attribute("jdbcType", c.getJdbcTypeName()));
            resultElement.addAttribute(new Attribute("javaType", c.getJavaTypeInfo().getFullyQualifiedName()));

            if (StringUtils.isNotBlank(c.getTypeHandler())) {
                resultElement.addAttribute(new Attribute("typeHandler", c.getTypeHandler()));
            }

            constructor.addElement(resultElement);
        }

        List<ColumnInfo> columns;
        if (isSimple) {
            columns = tableInfoWrapper.getNonPrimaryKeyColumns();
        } else {
            columns = tableInfoWrapper.getBaseColumns();
        }
        for (ColumnInfo c : columns) {
            XmlElement resultElement = new XmlElement("arg");

            resultElement.addAttribute(new Attribute(
                    "column", MyBatis3FormattingUtil.getRenamedColumnNameForResultMap(c)));
            resultElement.addAttribute(new Attribute("jdbcType",
                    c.getJdbcTypeName()));
            resultElement.addAttribute(new Attribute("javaType", c.getJavaTypeInfo().getFullyQualifiedName()));

            if (StringUtils.isNotBlank(c.getTypeHandler())) {
                resultElement.addAttribute(new Attribute("typeHandler", c.getTypeHandler()));
            }

            constructor.addElement(resultElement);
        }

        answer.addElement(constructor);
    }
}

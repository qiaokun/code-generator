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

/**
 * 基础resultMap（BLOB字段）
 * Author: CK
 * Date: 2015/6/7
 */
public class ResultMapWithBLOBsElementGenerator extends AbstractXmlElementGenerator {

    protected AbstractAttributes attributes;

    @Override
    public void getAttributes() {
        this.attributes = tableInfoWrapper.getAttributes();
    }

    @Override
    public void prepareXmlElement() {
        name = "resultMap";
        id = attributes.getResultMapWithBLOBs();
        String returnType;
        if (rules.generateRecordWithBLOBsClass()) {
            returnType = attributes.getRecordWithBLOBsType();
        } else {
            // table has BLOBs, but no BLOB class - BLOB fields must be
            // in the base class
            returnType = attributes.getBaseRecord();
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
        for (ColumnInfo c : tableInfoWrapper.getBlobColumns()) {
            XmlElement resultElement = new XmlElement("result");

            resultElement.addAttribute(new Attribute(
                    "column", MyBatis3FormattingUtil.getRenamedColumnNameForResultMap(c)));
            resultElement.addAttribute(new Attribute("property", c.getJavaProperty()));
            resultElement.addAttribute(new Attribute("jdbcType", c.getJdbcTypeName()));

            if (StringUtils.isNotBlank(c.getTypeHandler())) {
                resultElement.addAttribute(new Attribute("typeHandler", c.getTypeHandler()));
            }

            answer.addElement(resultElement);
        }
    }

    private void addResultMapConstructorElements(XmlElement answer) {
        XmlElement constructor = new XmlElement("constructor");

        for (ColumnInfo c : tableInfoWrapper.getPrimaryKeyColumns()) {
            XmlElement resultElement = new XmlElement("idArg");

            resultElement.addAttribute(new Attribute("column",
                    MyBatis3FormattingUtil.getRenamedColumnNameForResultMap(c)));
            resultElement.addAttribute(new Attribute("jdbcType", c.getJdbcTypeName()));
            resultElement.addAttribute(new Attribute("javaType", c.getJavaTypeInfo().getFullyQualifiedName()));

            if (StringUtils.isNotBlank(c.getTypeHandler())) {
                resultElement.addAttribute(new Attribute("typeHandler", c.getTypeHandler()));
            }

            constructor.addElement(resultElement);
        }

        for (ColumnInfo c : tableInfoWrapper.getNonPrimaryKeyColumns()) {
            XmlElement resultElement = new XmlElement("arg");

            resultElement.addAttribute(new Attribute("column",
                    MyBatis3FormattingUtil.getRenamedColumnNameForResultMap(c)));
            resultElement.addAttribute(new Attribute("jdbcType", c.getJdbcTypeName()));

            if (c.getJavaTypeInfo().isPrimitive()) {
                // need to use the MyBatis type alias for a primitive byte
                StringBuilder sb = new StringBuilder();
                sb.append('_');
                sb.append(c.getJavaTypeInfo().getShortName());
                resultElement.addAttribute(new Attribute("javaType", sb.toString()));
            } else if ("byte[]".equals(c.getJavaTypeInfo().getFullyQualifiedName())) {
                // need to use the MyBatis type alias for a primitive byte arry
                resultElement.addAttribute(new Attribute("javaType", "_byte[]"));
            } else {
                resultElement.addAttribute(new Attribute("javaType", c.getJavaTypeInfo().getFullyQualifiedName()));
            }

            if (StringUtils.isNotBlank(c.getTypeHandler())) {
                resultElement.addAttribute(new Attribute("typeHandler", c.getTypeHandler()));
            }

            constructor.addElement(resultElement);
        }

        answer.addElement(constructor);
    }
}

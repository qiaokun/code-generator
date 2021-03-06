/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api.comment.framework;

import cn.vansky.code.generator.api.comment.DefaultCommentGenerator;
import cn.vansky.code.generator.config.MergeConstants;
import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.Field;
import cn.vansky.code.generator.java.InnerClass;
import cn.vansky.code.generator.java.InnerEnum;
import cn.vansky.code.generator.java.Method;
import cn.vansky.code.generator.java.Parameter;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.framework.common.util.DateUtil;

import java.util.Date;

/**
 * 注解处理类
 * Author: CK
 * Date: 2015/6/7
 */
public class FrameWorkCommentGenerator extends DefaultCommentGenerator {
    /** 是否支持日期 */
    protected boolean suppressDate;

    /** 是否支持所有注释 */
    protected boolean suppressAllComments;

    public void addJavaFileComment(CompilationUnit compilationUnit) {
        compilationUnit.addFileCommentLine("/*");
        compilationUnit.addFileCommentLine(" * Copyright (C) " + DateUtil.year(new Date())
                + " CK, Inc. All Rights Reserved.");
        compilationUnit.addFileCommentLine(" */");
        compilationUnit.addFileCommentLine("");
    }

    public void addComment(XmlElement xmlElement) {
        if (suppressAllComments) {
            return;
        }

        xmlElement.addElement(new TextElement("<!--"));

        StringBuilder sb = new StringBuilder();
        sb.append("  WARNING - ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        xmlElement.addElement(new TextElement(sb.toString()));
        xmlElement.addElement(new TextElement(
                "  This element is automatically generated by MyBatis Generator, do not modify."));

        String s = getDateString();
        if (s != null) {
            sb.setLength(0);
            sb.append("  This element was generated on ");
            sb.append(s);
            sb.append('.');
            xmlElement.addElement(new TextElement(sb.toString()));
        }

        xmlElement.addElement(new TextElement("-->")); 
    }

    protected String getDateString() {
        if (suppressDate) {
            return null;
        } else {
            return DateUtil.format(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date());
        }
    }

    public void addClassComment(InnerClass innerClass, TableInfoWrapper tableInfoWrapper) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        innerClass.addJavaDocLine("/**"); 
        sb.append(" * This class corresponds to the database table ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        innerClass.addJavaDocLine(sb.toString());
        innerClass.addJavaDocLine(" */");
    }

    public void addEnumComment(InnerEnum innerEnum, TableInfoWrapper tableInfoWrapper) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**");
        sb.append(" * This enum corresponds to the database table ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        innerEnum.addJavaDocLine(sb.toString());
        innerEnum.addJavaDocLine(" */");
    }

    public void addFieldComment(Field field, TableInfoWrapper tableInfoWrapper, ColumnInfo columnInfo) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * This field corresponds to the database column ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        sb.append('.');
        sb.append(columnInfo.getActualColumnName());
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
    }

    public void addFieldComment(Field field, TableInfoWrapper tableInfoWrapper) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * This field corresponds to the database table ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
    }

    public void addGeneralMethodComment(Method method, TableInfoWrapper tableInfoWrapper) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * This method corresponds to the database table ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }

    public void addGetterComment(Method method, TableInfoWrapper tableInfoWrapper, ColumnInfo columnInfo) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * This method returns the value of the database column ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        sb.append('.');
        sb.append(columnInfo.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" *");
        sb.setLength(0);
        sb.append(" * @return the value of "); 
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        sb.append('.');
        sb.append(columnInfo.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }

    public void addSetterComment(Method method, TableInfoWrapper tableInfoWrapper, ColumnInfo columnInfo) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * This method sets the value of the database column ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        sb.append('.');
        sb.append(columnInfo.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" *");

        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param "); 
        sb.append(parm.getName());
        sb.append(" the value for "); 
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        sb.append('.');
        sb.append(columnInfo.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }

    public void addClassComment(InnerClass innerClass, TableInfoWrapper tableInfoWrapper,
                                boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * This class corresponds to the database table ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        innerClass.addJavaDocLine(sb.toString());
        innerClass.addJavaDocLine(" */");
    }
}

package cn.vansky.code.generator.api.comment.ppms;

import cn.vansky.code.generator.api.comment.DefaultCommentGenerator;
import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.Field;
import cn.vansky.code.generator.java.InnerClass;
import cn.vansky.code.generator.java.Method;
import cn.vansky.code.generator.java.Parameter;
import cn.vansky.framework.common.util.DateUtil;

import java.util.Date;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/15
 */
public class PPmsCommentGenerator extends DefaultCommentGenerator {
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        compilationUnit.addFileCommentLine("/*");
        compilationUnit.addFileCommentLine(" * Copyright (C) " + DateUtil.year(new Date())
                + " qianbao, Inc. All Rights Reserved.");
        compilationUnit.addFileCommentLine(" */");
        compilationUnit.addFileCommentLine("");
    }

    public void addFieldComment(Field field, TableInfoWrapper tableInfoWrapper, ColumnInfo columnInfo) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(columnInfo.getComment());
        field.addJavaDocLine(sb.toString());

        field.addJavaDocLine(" */");
    }

    public void addClassComment(InnerClass innerClass, TableInfoWrapper tableInfoWrapper) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        sb.append(" ");
        sb.append(tableInfoWrapper.getTableInfo().getRemarks());
        innerClass.addJavaDocLine(sb.toString());

        innerClass.addJavaDocLine(" */");
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
}

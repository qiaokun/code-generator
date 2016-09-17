/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.framework;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.java.Field;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.JavaTypeInfoEnum;
import cn.vansky.code.generator.java.Method;
import cn.vansky.code.generator.java.TopLevelClass;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.util.JavaBeansUtil;
import cn.vansky.framework.core.dao.annotation.ColumnDescription;
import cn.vansky.framework.core.dao.annotation.Id;

import java.util.HashSet;
import java.util.List;

/**
 * 基础类
 * Author: CK
 * Date: 2015/6/13
 */
public class FrameworkBaseBoGenerator extends AbstractJavaElementGenerator<FrameworkAttributes> {
    public FrameworkBaseBoGenerator(TableInfoWrapper<FrameworkAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getBaseBo();
        superClass = JavaTypeInfoEnum.FIELD_ACCESS_VO.getJavaTypeInfo();
        superInterfaces = new HashSet<JavaTypeInfo>();
        superInterfaces.add(JavaTypeInfoEnum.TABLE_DATA_CONVERTABLE.getJavaTypeInfo());
    }

    public void dealElement(TopLevelClass topLevelClass) {
        topLevelClass.setAbstract(true);

        List<ColumnInfo> columnInfos = getColumnsInThisClass();
        List<ColumnInfo> columnInfoList = tableInfoWrapper.getPrimaryKeyColumns();

        for (ColumnInfo columnInfo : columnInfos) {
            /** 字段常量 */
            Field fieldConstant = JavaBeansUtil.getJavaBeansFieldConstant(columnInfo, tableInfoWrapper);
            topLevelClass.addField(fieldConstant);

            /** 字段描述常量 */
            Field fieldRemark = JavaBeansUtil.getJavaBeansFieldRemark(columnInfo, tableInfoWrapper);
            topLevelClass.addField(fieldRemark);

            /** 字段属性 */
            Field field = JavaBeansUtil.getJavaBeansField(columnInfo, tableInfoWrapper);
            /** 字段@ColumnDescription(desc = "")注解 */
            field.addAnnotation("@" + ColumnDescription.class.getSimpleName()
                    + "(desc = " + fieldRemark.getName() + ")");
            topLevelClass.addImportedType(JavaTypeInfoEnum.COLUMN_DESCRIPTION.getJavaTypeInfo());
            /** 主键@Id注解 */
            for (ColumnInfo c : columnInfoList) {
                if (columnInfo.getActualColumnName().equals(c.getActualColumnName())) {
                    field.addAnnotation("@" + Id.class.getSimpleName());
                    topLevelClass.addImportedType(JavaTypeInfoEnum.ID.getJavaTypeInfo());
                    break;
                }
            }
            topLevelClass.addField(field);
            topLevelClass.addImportedType(field.getType());

            /** get方法 */
            Method method = JavaBeansUtil.getJavaBeansGetter(columnInfo, tableInfoWrapper);
            topLevelClass.addMethod(method);

            /** set方法 */
            method = JavaBeansUtil.getJavaBeansSetter(columnInfo, context, tableInfoWrapper);
            topLevelClass.addMethod(method);
        }
    }
}

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.ppms;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.Field;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.Method;
import cn.vansky.code.generator.java.TopLevelClass;
import cn.vansky.code.generator.util.JavaBeansUtil;

import java.util.List;

/**
 * 基础类
 * Author: CK
 * Date: 2015/6/13
 */
public class PPmsBaseBoGenerator extends PPmsJavaElementGenerator {
    public PPmsBaseBoGenerator(TableInfoWrapper tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getBo();
        superClass = new JavaTypeInfo("com.zrj.pay.core.model.BaseObject");
    }

    public void dealElement(TopLevelClass topLevelClass) {
        List<ColumnInfo> columnInfos = getColumnsInThisClass();

        for (ColumnInfo columnInfo : columnInfos) {
            /** 字段属性 */
            Field field = JavaBeansUtil.getJavaBeansField(columnInfo, tableInfoWrapper);
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

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.ppms;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.*;
import cn.vansky.code.generator.java.JavaTypeInfoEnum;
import cn.vansky.code.generator.util.JavaBeansUtil;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public class PPmsDaoGenerator extends PPmsJavaElementGenerator {
    public PPmsDaoGenerator(TableInfoWrapper tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    @Override
    public void prepareElement() {
        javaTypeInfo = attributes.getDao();
        superClass = new JavaTypeInfo("com.zrj.pay.core.dao.BaseDao");
    }

    @Override
    public void dealElement(TopLevelClass topLevelClass) {
        topLevelClass.setInterface(true);
        JavaTypeInfo bo = attributes.getBo();
        topLevelClass.addImportedType(bo);
        /** 类注解@Repository */
        topLevelClass.addAnnotation("@Repository(\""
                + JavaBeansUtil.getValidPropertyName(javaTypeInfo.getShortName()) + "\")");
        topLevelClass.addImportedType(JavaTypeInfoEnum.REPOSITORY.getJavaTypeInfo());

        topLevelClass.addImportedType(JavaTypeInfoEnum.LIST.getJavaTypeInfo());
        JavaTypeInfo javaTypeInfo = new JavaTypeInfo("List<" + bo.getShortName() + ">");
        Parameter parameter = new Parameter(javaTypeInfo, "list");
        Method method = new Method("insertBatch");
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.addParameter(parameter);
        topLevelClass.addMethod(method);
    }
}

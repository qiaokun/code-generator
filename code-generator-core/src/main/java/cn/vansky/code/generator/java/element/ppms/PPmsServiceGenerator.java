/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.ppms;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.java.JavaKeywords;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.JavaTypeInfoEnum;
import cn.vansky.code.generator.java.Method;
import cn.vansky.code.generator.java.Parameter;
import cn.vansky.code.generator.java.TopLevelClass;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public class PPmsServiceGenerator extends AbstractJavaElementGenerator<PPmsAttributes> {
    public PPmsServiceGenerator(TableInfoWrapper<PPmsAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getService();
        JavaTypeInfo bo = attributes.getBo();
        superClass = new JavaTypeInfo("com.zrj.pay.core.service.BaseTransactionService<" + bo.getShortName() + ">");
    }

    public void dealElement(TopLevelClass topLevelClass) {
        topLevelClass.setInterface(true);
        JavaTypeInfo bo = attributes.getBo();
        topLevelClass.addImportedType(bo);

        topLevelClass.addImportedType(JavaTypeInfoEnum.LIST.getJavaTypeInfo());
        JavaTypeInfo params = new JavaTypeInfo(List.class.getName() + "<" + bo.getShortName() + ">");
        Parameter parameter = new Parameter(params, "list");
        Method method = new Method("$insertBatch");
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.addParameter(parameter);
        topLevelClass.addMethod(method);
    }
}

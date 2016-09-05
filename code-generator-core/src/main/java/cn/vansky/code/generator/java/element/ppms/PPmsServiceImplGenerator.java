/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.ppms;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.java.*;
import cn.vansky.code.generator.java.JavaTypeInfoEnum;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.util.JavaBeansUtil;

import java.util.HashSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public class PPmsServiceImplGenerator extends AbstractJavaElementGenerator<PPmsAttributes> {
    public PPmsServiceImplGenerator(TableInfoWrapper<PPmsAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    @Override
    public void prepareElement() {
        javaTypeInfo = attributes.getServiceImpl();
        JavaTypeInfo bo = attributes.getBo();
        superClass = new JavaTypeInfo("com.zrj.pay.core.service.BaseTransactionServiceImpl<" + bo.getShortName() + ">");
        superInterfaces = new HashSet<JavaTypeInfo>();
        superInterfaces.add(attributes.getService());
    }

    @Override
    public void dealElement(TopLevelClass topLevelClass) {
        JavaTypeInfo bo = attributes.getBo();
        topLevelClass.addImportedType(bo);
        topLevelClass.addImportedType(JavaTypeInfoEnum.LIST.getJavaTypeInfo());
        // dao field
        JavaTypeInfo dao = attributes.getDao();
        String shortName = JavaBeansUtil.getValidPropertyName(dao.getShortName());
        Field field = new Field(shortName, dao);
        field.setJavaScope(JavaKeywords.PRIVATE);
        topLevelClass.addField(field);
        topLevelClass.addImportedType(dao);

        JavaTypeInfo params = new JavaTypeInfo(List.class.getName() + "<" + bo.getShortName() + ">");
        Parameter parameter = new Parameter(params, "list");
        Method method = new Method("$insertBatch");
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.addParameter(parameter);
        StringBuilder sb = new StringBuilder();
        // method
        sb.append(shortName);
        sb.append(".insertBatch(list);");
        Method tmpMethod = new Method(method);
        tmpMethod.addBodyLine(sb.toString());
        topLevelClass.addMethod(tmpMethod);

        tmpMethod = new Method(JavaBeansUtil.getSetterMethodName(shortName));
        tmpMethod.setJavaScope(JavaKeywords.PUBLIC);
        tmpMethod.addParameter(new Parameter(dao, shortName));
        sb.setLength(0);
        sb.append("this.");
        sb.append(shortName);
        sb.append(" = ");
        sb.append(shortName);
        sb.append(';');
        tmpMethod.addBodyLine(sb.toString());
        topLevelClass.addMethod(tmpMethod);
    }
}

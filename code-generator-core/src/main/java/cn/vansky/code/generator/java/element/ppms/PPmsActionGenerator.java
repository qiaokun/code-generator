/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.ppms;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.java.Field;
import cn.vansky.code.generator.java.JavaKeywords;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.Method;
import cn.vansky.code.generator.java.Parameter;
import cn.vansky.code.generator.java.TopLevelClass;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.util.JavaBeansUtil;

/**
 * 基础类
 * Author: CK
 * Date: 2015/6/13
 */
public class PPmsActionGenerator extends AbstractJavaElementGenerator<PPmsAttributes> {
    public PPmsActionGenerator(TableInfoWrapper<PPmsAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getController();
        superClass = new JavaTypeInfo("com.zrj.pay.core.action.BaseController");
    }

    public void dealElement(TopLevelClass topLevelClass) {
        // service field
        JavaTypeInfo service = attributes.getService();
        String shortName = JavaBeansUtil.getValidPropertyName(service.getShortName());
        Field field = new Field(shortName, service);
        field.setJavaScope(JavaKeywords.PRIVATE);
        topLevelClass.addField(field);
        topLevelClass.addImportedType(service);

        // method
        Method method = new Method("execute");
        JavaTypeInfo typeInfo = new JavaTypeInfo("org.springframework.web.servlet.ModelAndView");
        method.setReturnType(typeInfo);
        topLevelClass.addImportedType(typeInfo);
        method.setJavaScope(JavaKeywords.PUBLIC);
        JavaTypeInfo params = new JavaTypeInfo("javax.servlet.http.HttpServletRequest");
        Parameter parameter = new Parameter(params, "request");
        method.addParameter(parameter);
        topLevelClass.addImportedType(params);

        params = new JavaTypeInfo("javax.servlet.http.HttpServletResponse");
        parameter = new Parameter(params, "response");
        method.addParameter(parameter);
        topLevelClass.addImportedType(params);

        StringBuilder sb = new StringBuilder();
        sb.append("ModelAndViewObject mavo = new ModelAndViewObject(\"\");");
        method.addBodyLine(sb.toString());
        sb.setLength(0);
        sb.append("return mavo.getMAV();");
        method.addBodyLine(sb.toString());
        topLevelClass.addMethod(method);
        typeInfo = new JavaTypeInfo("com.zrj.pay.core.model.ModelAndViewObject");
        topLevelClass.addImportedType(typeInfo);

        method = new Method(JavaBeansUtil.getSetterMethodName(shortName));
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.addParameter(new Parameter(service, shortName));
        sb.setLength(0);
        sb.append("this.");
        sb.append(shortName);
        sb.append(" = ");
        sb.append(shortName);
        sb.append(';');
        method.addBodyLine(sb.toString());
        topLevelClass.addMethod(method);

        // log service field
        JavaTypeInfo log = new JavaTypeInfo("com.zrj.pay.ppms.tx.ppms.PpmsOpLogTransactionService");
        shortName = JavaBeansUtil.getValidPropertyName(log.getShortName());
        field = new Field(shortName, log);
        field.setJavaScope(JavaKeywords.PRIVATE);
        topLevelClass.addField(field);
        topLevelClass.addImportedType(log);

        method = new Method(JavaBeansUtil.getSetterMethodName(shortName));
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.addParameter(new Parameter(log, shortName));
        sb.setLength(0);
        sb.append("this.");
        sb.append(shortName);
        sb.append(" = ");
        sb.append(shortName);
        sb.append(';');
        method.addBodyLine(sb.toString());
        topLevelClass.addMethod(method);
    }
}

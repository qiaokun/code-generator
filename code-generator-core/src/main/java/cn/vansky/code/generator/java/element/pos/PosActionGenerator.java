/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.pos;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.java.*;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.util.JavaBeansUtil;

/**
 * 基础类
 * Author: CK
 * Date: 2015/6/13
 */
public class PosActionGenerator extends AbstractJavaElementGenerator<PosAttributes> {
    public PosActionGenerator(TableInfoWrapper<PosAttributes> tableInfoWrapper) {
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
        field.addAnnotation("@Resource(name = \"" + shortName +"\")");
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
        method.addBodyLine("try {");

        sb.setLength(0);
        sb.append(attributes.getPageVo().getShortName());
        sb.append(" page = RequestParamsUtil.convertPage(request, mavo, ");
        sb.append(attributes.getPageVo().getShortName());
        sb.append(".class);");
        method.addBodyLine(sb.toString());
        topLevelClass.addImportedType(attributes.getPageVo());
        topLevelClass.addImportedType(attributes.getBo());

        sb.setLength(0);
        sb.append("List<");
        sb.append(attributes.getBo().getShortName());
        sb.append("> list = ");
        sb.append(shortName);
        sb.append(".findPage(page);");
        method.addBodyLine(sb.toString());
        topLevelClass.addImportedType(attributes.getBo());
        topLevelClass.addImportedType(JavaTypeInfoEnum.LIST.getJavaTypeInfo());

        method.addBodyLine("mavo.setList(list);");
        method.addBodyLine("} catch (Exception t) {");
        method.addBodyLine("logger.error(\"查询数据库异常{}\", t);");
        method.addBodyLine("}");
        sb.setLength(0);
        sb.append("return mavo.getMAV();");
        method.addBodyLine(sb.toString());
        topLevelClass.addMethod(method);
        typeInfo = new JavaTypeInfo("com.zrj.pay.core.model.ModelAndViewObject");
        topLevelClass.addImportedType(typeInfo);

        // log service field
        JavaTypeInfo log = new JavaTypeInfo("com.zrj.pay.pos.ppms.tx.log.PpmsOpLogTransactionService");
        shortName = JavaBeansUtil.getValidPropertyName(log.getShortName());
        field = new Field(shortName, log);
        field.setJavaScope(JavaKeywords.PRIVATE);
        field.addAnnotation("@Resource(name = \"" + shortName +"\")");
        topLevelClass.addField(field);
        topLevelClass.addImportedType(log);
        topLevelClass.addImportedType(JavaTypeInfoEnum.RESOURCE.getJavaTypeInfo());
        topLevelClass.addImportedType(new JavaTypeInfo("com.zrj.pay.pos.ppms.util.RequestParamsUtil"));
    }
}

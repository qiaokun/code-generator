/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.framework;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.java.Field;
import cn.vansky.code.generator.java.JavaKeywords;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.java.JavaTypeInfoEnum;
import cn.vansky.code.generator.java.Method;
import cn.vansky.code.generator.java.Parameter;
import cn.vansky.code.generator.java.TopLevelClass;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.util.JavaBeansUtil;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public class FrameworkDaoImplGenerator extends AbstractJavaElementGenerator<FrameworkAttributes> {
    public FrameworkDaoImplGenerator(TableInfoWrapper<FrameworkAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getDaoImpl();
        superClass = attributes.getConfigurableBaseSqlMapDao();
        superInterfaces = new HashSet<JavaTypeInfo>();
        superInterfaces.add(attributes.getDao());
    }

    public void dealElement(TopLevelClass topLevelClass) {
        topLevelClass.addImportedType(attributes.getBo());

        JavaTypeInfo dao = attributes.getDao();
        /** 类注解@Repository */
        topLevelClass.addAnnotation("@Repository(\""
                + JavaBeansUtil.getValidPropertyName(dao.getShortName()) + "\")");
        topLevelClass.addImportedType(JavaTypeInfoEnum.REPOSITORY.getJavaTypeInfo());

        /** 字段@Autowired */
        JavaTypeInfo mapper = attributes.getMapper();
        Field field = new Field(JavaBeansUtil.getValidPropertyName(mapper.getShortName()), mapper);
        field.setJavaScope(JavaKeywords.PRIVATE);
        field.addAnnotation("@Autowired");
        topLevelClass.addField(field);
        topLevelClass.addImportedType(JavaTypeInfoEnum.AUTOWIRED.getJavaTypeInfo());
        topLevelClass.addImportedType(mapper);

        JavaTypeInfo daoMapper = attributes.getDaoMapper();
        Method getDaoMapper = new Method("getDaoMapper");
        getDaoMapper.setJavaScope(JavaKeywords.PUBLIC);
        getDaoMapper.addBodyLine("return " + JavaBeansUtil.getValidPropertyName(mapper.getShortName()) + ";");
        getDaoMapper.setReturnType(daoMapper);
        topLevelClass.addMethod(getDaoMapper);
        topLevelClass.addImportedType(daoMapper);

        Method setSqlSessionFactory = new Method("setSqlSessionFactory");
        setSqlSessionFactory.setJavaScope(JavaKeywords.PUBLIC);
        setSqlSessionFactory.addBodyLine("setSqlSessionFactoryInternal(sqlSessionFactory);");
        String transactionName =
                context.getTransactionName() != null ? context.getTransactionName() : "sqlSessionFactory";
        setSqlSessionFactory.addAnnotation("@Resource(name = \"" + transactionName + "\")");
        topLevelClass.addImportedType(JavaTypeInfoEnum.RESOURCE.getJavaTypeInfo());
        setSqlSessionFactory.addParameter(new Parameter(JavaTypeInfoEnum.SQL_SESSION_FACTORY.getJavaTypeInfo(), "sqlSessionFactory"));
        topLevelClass.addMethod(setSqlSessionFactory);
        topLevelClass.addImportedType(JavaTypeInfoEnum.SQL_SESSION_FACTORY.getJavaTypeInfo());

        if (getPk()) {
            topLevelClass.addImportedType(JavaTypeInfoEnum.HASH_MAP.getJavaTypeInfo());
        }
    }
}

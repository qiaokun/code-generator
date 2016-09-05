/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.framework;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.java.*;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.util.JavaBeansUtil;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public class FrameworkServiceImplGenerator extends AbstractJavaElementGenerator<FrameworkAttributes> {
    public FrameworkServiceImplGenerator(TableInfoWrapper<FrameworkAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getServiceImpl();
//        if (2 == context.getTransactionType()) {
//            /** 注解配置 */
//            superClass = new JavaTypeInfo(AnnotationGenericSqlMapServiceImpl.class.getName() + s);
//        } else {
            /** XML配置父类GenericSqlMapServiceImpl */
            superClass = attributes.getGenericSqlMapServiceImpl();
//        }
        superInterfaces = new HashSet<JavaTypeInfo>();
        superInterfaces.add(attributes.getService());
    }

    public void dealElement(TopLevelClass topLevelClass) {
//        if (2 == context.getTransactionType()) {
//            /** 事务注解@Transactional */
//            topLevelClass.addAnnotation("@Transactional");
//        }

        JavaTypeInfo bo = attributes.getBo();
        topLevelClass.addImportedType(bo);

        JavaTypeInfo service = attributes.getService();
        /** 类注解@Repository */
        topLevelClass.addAnnotation("@Service(\""
                + JavaBeansUtil.getValidPropertyName(service.getShortName()) + "\")");
        topLevelClass.addImportedType(JavaTypeInfoEnum.SERVICE.getJavaTypeInfo());

        /** 字段 */
        JavaTypeInfo dao = attributes.getDao();
        topLevelClass.addImportedType(dao);
        String shortName = JavaBeansUtil.getValidPropertyName(dao.getShortName());
        Field field = new Field(shortName, dao);
        field.setJavaScope(JavaKeywords.PRIVATE);
        field.addAnnotation("@Resource(name = \"" + shortName +"\")");
        topLevelClass.addField(field);
        topLevelClass.addImportedType(JavaTypeInfoEnum.RESOURCE.getJavaTypeInfo());

        Method getDao = new Method("getDao");
        getDao.setJavaScope(JavaKeywords.PUBLIC);
        getDao.addBodyLine("return " + shortName + ";");
        JavaTypeInfo sqlMapDao = attributes.getSqlMapDao();
        getDao.setReturnType(sqlMapDao);
        topLevelClass.addMethod(getDao);
        topLevelClass.addImportedType(sqlMapDao);

        if (getPk()) {
            topLevelClass.addImportedType(JavaTypeInfoEnum.HASH_MAP.getJavaTypeInfo());
        }
    }
}

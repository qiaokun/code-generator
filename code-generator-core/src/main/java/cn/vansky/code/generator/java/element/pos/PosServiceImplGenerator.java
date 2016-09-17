/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.pos;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.pos.PosAttributes;
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
public class PosServiceImplGenerator extends AbstractJavaElementGenerator<PosAttributes> {
    public PosServiceImplGenerator(TableInfoWrapper<PosAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getServiceImpl();
        superClass = attributes.getGenericSqlMapServiceImpl();
        superInterfaces = new HashSet<JavaTypeInfo>();
        superInterfaces.add(attributes.getService());
    }

    public void dealElement(TopLevelClass topLevelClass) {
        JavaTypeInfo bo = attributes.getBo();
        topLevelClass.addImportedType(bo);
        topLevelClass.addImportedType(JavaTypeInfoEnum.LIST.getJavaTypeInfo());

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
        String daoAnnotation = "@Resource(name = \"" + shortName +"\")";
        field.setJavaScope(JavaKeywords.PRIVATE);
        field.addAnnotation(daoAnnotation);
        topLevelClass.addField(field);
        topLevelClass.addImportedType(JavaTypeInfoEnum.RESOURCE.getJavaTypeInfo());

        Method setSqlMapDao = new Method("setSqlMapDao");
        setSqlMapDao.setJavaScope(JavaKeywords.PUBLIC);
        setSqlMapDao.addBodyLine("setBaseDao(sqlMapDao);");
        JavaTypeInfo sqlMapDao = attributes.getSqlMapDao();
        Parameter parameter = new Parameter(sqlMapDao, "sqlMapDao");
        setSqlMapDao.addParameter(parameter);
        setSqlMapDao.addAnnotation(daoAnnotation);
        topLevelClass.addMethod(setSqlMapDao);
        topLevelClass.addImportedType(sqlMapDao);

        if (getPk()) {
            topLevelClass.addImportedType(JavaTypeInfoEnum.HASH_MAP.getJavaTypeInfo());
        }
    }
}

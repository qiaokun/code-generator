/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.framework;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.java.JavaTypeInfoEnum;
import cn.vansky.code.generator.java.TopLevelClass;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public class FrameworkMapperGenerator extends AbstractJavaElementGenerator<FrameworkAttributes> {
    public FrameworkMapperGenerator(TableInfoWrapper<FrameworkAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getMapper();
        superClass = attributes.getDaoMapper();
    }

    public void dealElement(TopLevelClass topLevelClass) {
        topLevelClass.setInterface(true);
        topLevelClass.addImportedType(attributes.getBo());
        topLevelClass.addAnnotation("@SqlMapper");

        topLevelClass.addImportedType(JavaTypeInfoEnum.SQL_MAPPER.getJavaTypeInfo());
        if (getPk()) {
            topLevelClass.addImportedType(JavaTypeInfoEnum.HASH_MAP.getJavaTypeInfo());
        }
    }
}

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.framework;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.*;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public class FrameworkMapperGenerator extends FrameworkJavaElementGenerator {
    public FrameworkMapperGenerator(TableInfoWrapper tableInfoWrapper) {
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

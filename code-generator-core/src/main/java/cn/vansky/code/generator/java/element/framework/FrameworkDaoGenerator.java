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
public class FrameworkDaoGenerator extends AbstractJavaElementGenerator<FrameworkAttributes> {
    public FrameworkDaoGenerator(TableInfoWrapper<FrameworkAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getDao();
        superClass = attributes.getSqlMapDao();
    }

    public void dealElement(TopLevelClass topLevelClass) {
        topLevelClass.setInterface(true);
        topLevelClass.addImportedType(attributes.getBo());

        if (getPk()) {
            topLevelClass.addImportedType(JavaTypeInfoEnum.HASH_MAP.getJavaTypeInfo());
        }
    }
}

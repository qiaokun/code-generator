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
public class FrameworkDaoGenerator extends FrameworkJavaElementGenerator {
    public FrameworkDaoGenerator(TableInfoWrapper tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    @Override
    public void prepareElement() {
        javaTypeInfo = attributes.getDao();
        superClass = attributes.getSqlMapDao();
    }

    @Override
    public void dealElement(TopLevelClass topLevelClass) {
        topLevelClass.setInterface(true);
        topLevelClass.addImportedType(attributes.getBo());

        if (getPk()) {
            topLevelClass.addImportedType(JavaTypeInfoEnum.HASH_MAP.getJavaTypeInfo());
        }
    }
}

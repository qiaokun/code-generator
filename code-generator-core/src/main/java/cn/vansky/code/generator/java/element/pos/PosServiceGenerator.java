/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.pos;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.java.*;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public class PosServiceGenerator extends AbstractJavaElementGenerator<PosAttributes> {
    public PosServiceGenerator(TableInfoWrapper<PosAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    @Override
    public void prepareElement() {
        javaTypeInfo = attributes.getService();
        superClass = attributes.getGenericService();
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

/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java.element.framework;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.java.TopLevelClass;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;

/**
 * 基础类
 * Author: CK
 * Date: 2015/6/13
 */
public class FrameworkExtendsBoGenerator extends AbstractJavaElementGenerator<FrameworkAttributes> {
    public FrameworkExtendsBoGenerator(TableInfoWrapper<FrameworkAttributes> tableInfoWrapper) {
        super(tableInfoWrapper);
    }

    public void prepareElement() {
        javaTypeInfo = attributes.getBo();
        superClass = attributes.getBaseBo();
    }

    public void dealElement(TopLevelClass topLevelClass) {

    }
}

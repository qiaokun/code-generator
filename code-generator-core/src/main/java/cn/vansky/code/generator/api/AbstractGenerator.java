/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.api;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.config.rule.Rules;
import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.db.TableInfoWrapper;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/13
 */
public abstract class AbstractGenerator<T extends AbstractAttributes> {

    protected CodeGenContext context;

    protected TableInfoWrapper<T> tableInfoWrapper;

    protected Rules rules;

    protected CommentGenerator commentGenerator;

    public void setTableInfoWrapper(TableInfoWrapper<T> tableInfoWrapper) {
        this.tableInfoWrapper = tableInfoWrapper;
        this.context = tableInfoWrapper.getContext();
        this.rules = tableInfoWrapper.getRules();
        this.commentGenerator = tableInfoWrapper.getCommentGenerator();
    }
}

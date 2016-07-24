/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.factory;

import cn.vansky.code.generator.config.rule.BaseRules;
import cn.vansky.code.generator.config.rule.ExampleRules;
import cn.vansky.code.generator.config.rule.Rules;

/**
 * 规则工厂
 * Author: CK
 * Date: 2015/7/9.
 */
public class RuleFactory {
    private static RuleFactory instance = new RuleFactory();

    private RuleFactory() {

    }

    public static RuleFactory getInstance() {
        return instance;
    }

    public Rules getRules(RuleEnum ruleEnum) {
        if (RuleEnum.BASE_RULE.equals(ruleEnum)) {
            return new BaseRules();
        } else if (RuleEnum.EXAMPLE_RULE.equals(ruleEnum)) {
            return new ExampleRules();
        }
        throw new RuntimeException("没有相应的Rule类: RuleFactory");
    }


    public static enum RuleEnum {
        // 基础规则
        BASE_RULE("BASE_RULE"),
        // 样例规则
        EXAMPLE_RULE("EXAMPLE_RULE");

        private String rule;

        private RuleEnum(String s) {
            this.rule = s;
        }
    }
}

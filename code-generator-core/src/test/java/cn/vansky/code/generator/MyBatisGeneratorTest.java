/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator;

import cn.vansky.code.generator.api.MyBatisGenerator;
import cn.vansky.code.generator.config.CodeGenContext;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MyBatisGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        List<String> keyList = new ArrayList<String>();
        List<String> noKeyList = new ArrayList<String>();
        noKeyList.add("tb_user_history");
        CodeGenContext context = new CodeGenContext();
        context.setClasspath("cn.vansky");
        context.setProjectName("codeGen");
        context.setUrl("jdbc:mysql://localhost:3306/framework");
        context.setUser("root");
        context.setPassword("root");
        context.setTableNameList(keyList);
        context.setDatabaseByUrl("jdbc:mysql://localhost:3306/framework");

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(context);
        myBatisGenerator.generate();
    }

    @Test
    public void testGenerateExample() throws Exception {
        List<String> keyList = new ArrayList<String>();
        keyList.add("tb_role");
        keyList.add("tb_user");
        List<String> noKeyList = new ArrayList<String>();
        CodeGenContext context = new CodeGenContext();
        context.setClasspath("cn.vansky");
        context.setProjectName("codeGen");
        context.setUrl("jdbc:mysql://localhost:3306/framework");
        context.setUser("root");
        context.setPassword("root");
        context.setTableNameList(keyList);
//        context.setMapperGeneratorEnum(MapperGeneratorFactory.MapperGeneratorEnum.EXAMPLE_MAPPER);
//        context.setRuleEnum(RuleFactory.RuleEnum.EXAMPLE_RULE);
        context.setDatabaseByUrl("jdbc:mysql://localhost:3306/framework");

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(context);
        myBatisGenerator.generate();
    }
}
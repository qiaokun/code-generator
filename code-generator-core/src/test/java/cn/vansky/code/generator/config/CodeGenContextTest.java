/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.config;

import java.util.ArrayList;
import java.util.List;

public class CodeGenContextTest {

    public static CodeGenContext build() {
        CodeGenContext context = new CodeGenContext();
        context.setUrl("jdbc:mysql://localhost:3306/framework");
        context.setUser("root");
        context.setPassword("root");
        context.setDatabase("framework");
        context.setProjectName("codeGen");
        context.setClasspath("cn.vansky");
        List<String> list = new ArrayList<String>();
        list.add("role");
        context.setTableNameList(list);
        return context;
    }
}
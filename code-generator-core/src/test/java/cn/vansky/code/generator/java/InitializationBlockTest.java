package cn.vansky.code.generator.java;

import org.testng.annotations.Test;


public class InitializationBlockTest {

    @Test
    public void testGetFormattedContent() throws Exception {
        InitializationBlock initializationBlock = new InitializationBlock();
        initializationBlock.addBodyLine("Map<String, Object> map = new HashMap<String, Object>();");
        initializationBlock.addBodyLine("map.put(\"a\", \"a\");");
        initializationBlock.addBodyLine("map.put(\"b\", \"b\");");
        initializationBlock.addJavaDocLine("// map of demo ");
        String s = initializationBlock.getFormattedContent(0);
        System.out.println(s);
    }

    @Test
    public void testGetFormattedContent1() throws Exception {
        InitializationBlock initializationBlock = new InitializationBlock(true);
        initializationBlock.addBodyLine("Map<String, Object> map = new HashMap<String, Object>();");
        initializationBlock.addBodyLine("map.put(\"a\", \"a\");");
        initializationBlock.addBodyLine("map.put(\"b\", \"b\");");
        initializationBlock.addJavaDocLine("// map of demo ");
        String s = initializationBlock.getFormattedContent(0);
        System.out.println(s);
    }
}
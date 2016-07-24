package cn.vansky.code.generator.java;

import org.testng.annotations.Test;

public class FieldTest {

    @Test
    public void testGetFormattedContent() throws Exception {
        Field field = new Field("name", new JavaTypeInfo("java.lang.String"));
        String s = field.getFormattedContent(0);
        System.out.println(s);
    }
}
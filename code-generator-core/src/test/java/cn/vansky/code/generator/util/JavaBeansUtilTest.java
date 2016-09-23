/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JavaBeansUtilTest {

    @Test
    public void testDataBaseToJava() throws Exception {
        assertEquals("Role", JavaBeansUtil.dataBaseToJava("tb_role", 1));
        assertEquals("roleId", JavaBeansUtil.dataBaseToJava("role_id", 2));
    }

    @Test
    public void testGetValidPropertyName() throws Exception {
        assertEquals("XAxis", JavaBeansUtil.getValidPropertyName("XAxis"));
        assertEquals("yaxis", JavaBeansUtil.getValidPropertyName("Yaxis"));
        assertEquals("y", JavaBeansUtil.getValidPropertyName("Y"));
        assertEquals(null, JavaBeansUtil.getValidPropertyName(null));
    }
}
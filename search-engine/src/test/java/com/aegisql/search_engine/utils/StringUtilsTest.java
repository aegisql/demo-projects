package com.aegisql.search_engine.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void firstWords() {
        String s = StringUtils.firstWords(" to be or not to be ", 4);
        System.out.println(s);
        assertEquals("to be or not",s);
    }

    @Test
    public void lastWords() {

        String s = StringUtils.lastWords(" to be or not to be ", 4);
        System.out.println(s);
        assertEquals("or not to be",s);

    }
}
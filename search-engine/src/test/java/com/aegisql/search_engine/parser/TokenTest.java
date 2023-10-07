package com.aegisql.search_engine.parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenTest {

    @Test
    public void equalityTest() {
        Token t1 = new Token(100,1000,"A", new StringStreamSupplier("A"));
        Token t2 = new Token(101,1010,"A", new StringStreamSupplier("A"));
        assertFalse(t1.equals(t2));
        assertTrue(t1.contentEquals(t2));
        assertTrue(t2.contentEquals(t1));
        assertTrue(t1.contentEquals("A"));
        assertTrue(t1.contentEquals("a"));

    }

    @Test
    public void offsetTest() {
        Token t1 = new Token(1000,100,"A", new StringStreamSupplier("A"));
        Token t2 = new Token(1010,101,"A", new StringStreamSupplier("A"));

        assertEquals(-10,t1.getRelativeAbsOffset(t2));
        assertEquals(-1,t1.getRelativeTokenOffset(t2));

        assertEquals(10,t2.getRelativeAbsOffset(t1));
        assertEquals(1,t2.getRelativeTokenOffset(t1));

    }
}
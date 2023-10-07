package com.aegisql.search_engine.parser;

import com.aegisql.search_engine.utils.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

// More files in /Volumes/SDCard/Books/gutenberg/aleph.gutenberg.org

public class ParserTest {

    @Test
    public void basicParserTest() {
        List<Token> tokens = new ArrayList<>();
        Parser p = new Parser(token -> {
            System.out.println(token);
            tokens.add(token);
        }, new StringStreamSupplier("A and B"));
        p.parse();
        assertEquals(3,tokens.size());
    }

    @Test
    public void basicParserAcceptsNewLinesAndTabsTest() {
        List<Token> tokens = new ArrayList<>();
        Parser p = new Parser(token -> {
            System.out.println(token);
            tokens.add(token);
        }, new StringStreamSupplier("A\tand\nB"));
        p.parse();
        assertEquals(3,tokens.size());
    }

    @Test
    public void parseFileTest() {
        List<Token> tokens = new ArrayList<>();
        Parser p = new Parser(token -> {
            System.out.println(token);
            tokens.add(token);
        }, new FileStreamSupplier("src/test/resources/1110.txt"));

        p.parse();
        System.out.println("Total parsed: "+p.getSize());
        String s = FileUtils.readFragment(Path.of("src/test/resources/1110.txt"), 145780, 20);
        System.out.println("Fragment: "+s);
    }

}
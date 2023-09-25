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

public class ParserTest {

    @Test
    public void basicParserTest() {
        List<Token> tokens = new ArrayList<>();
        Parser p = new Parser(token -> {
            System.out.println(token);
            tokens.add(token);
        });
        p.parse("A and B");
        assertEquals(3,tokens.size());
    }

    @Test
    public void basicParserAcceptsNewLinesAndTabsTest() {
        List<Token> tokens = new ArrayList<>();
        Parser p = new Parser(token -> {
            System.out.println(token);
            tokens.add(token);
        });
        p.parse("A\tand\nB");
        assertEquals(3,tokens.size());
    }

    @Test
    public void parseFileTest() {
        List<Token> tokens = new ArrayList<>();
        Parser p = new Parser(token -> {
            System.out.println(token);
            tokens.add(token);
        });

        Stream<Character> characterStream = FileUtils.toCharStream("/Volumes/SDCard/Books/gutenberg/aleph.gutenberg.org/1/1/1/1110/1110.txt");

        p.parse(characterStream);
        System.out.println("Total parsed: "+p.getSize());
        String s = FileUtils.readFragment(Path.of("/Volumes/SDCard/Books/gutenberg/aleph.gutenberg.org/1/1/1/1110/1110.txt"), 145780, 20);
        System.out.println("Fragment: "+s);
    }

}
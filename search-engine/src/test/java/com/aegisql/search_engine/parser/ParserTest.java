package com.aegisql.search_engine.parser;

import com.aegisql.search_engine.utils.FileUtils;
import org.junit.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

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
            //System.out.println(token);
            tokens.add(token);
        }, new FileStreamSupplier("src/test/resources/1110.txt"));

        p.parse();
        System.out.println("Total parsed: "+p.getSize());
        List<Long> positions = tokens.stream().map(t -> (long) t.getAbsOffset()).sorted().collect(Collectors.toList());

        String s = FileUtils.readFragment(Path.of("src/test/resources/1110.txt"), 145780, 20, 10);
        System.out.println("Fragment: "+s);

        List<String> res = FileUtils.readFragments(Path.of("src/test/resources/1110.txt"), positions, 40);
        res.stream().limit(10).forEach(System.out::println);

    }

}
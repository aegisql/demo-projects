package com.aegisql.search_engine.index;

import com.aegisql.conveyor.Conveyor;
import com.aegisql.search_engine.FindAndIterateFilesTest;
import com.aegisql.search_engine.parser.FileStreamSupplier;
import com.aegisql.search_engine.parser.Parser;
import com.aegisql.search_engine.search.QueryType;
import com.aegisql.search_engine.search.SearchConveyor;
import com.aegisql.search_engine.search.SearchEvents;
import com.aegisql.search_engine.search.SearchQuery;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class IndexConveyorTest {

    @Test
    public void testFile() {
        IndexConveyor ic = new IndexConveyor();
        SearchConveyor sc = new SearchConveyor();
        Parser p = new Parser(t -> {
            ic.part().id(t.getToken()).label(IndexEvents.ADD_TOKEN).value(t).place();
        }, new FileStreamSupplier("src/test/resources/1110.txt"));
        p.parse();
        SearchQuery query = new SearchQuery("sovereign", 1L, QueryType.ANY);
        sc.part().ttl(Duration.ofSeconds(1)).id(1L).label(SearchEvents.INIT).value(query).place();
        ic.part().id("sovereign").label(IndexEvents.COLLECT).value(query).place();
        sc.completeAndStop().join();
        ic.stop();
    }

    @Test
    public void testDirectory() {

        // Change the directory path to the root folder of your search
        Path directory = Path.of("/Volumes/SDCard/Books/gutenberg/");
        String mask = "*.txt"; // Change this to the file mask you want to search

        try {
            Files.walkFileTree(directory, new CustomFileVisitor(mask,100));
        } catch (IOException e) {
            System.err.println("Error searching files: " + e.getMessage());
        }
        IndexConveyor ic = (IndexConveyor) Conveyor.byName("IndexConveyor");
        SearchConveyor sc = new SearchConveyor();
        SearchQuery query = new SearchQuery("sovereign", 1L, QueryType.ANY);
        sc.part().ttl(Duration.ofSeconds(1)).id(1L).label(SearchEvents.INIT).value(query).place();
        ic.part().id("sovereign").label(IndexEvents.COLLECT).value(query).place();
        sc.completeAndStop().join();
        ic.completeAndStop().join();
    }

    private static class CustomFileVisitor extends SimpleFileVisitor<Path> {
        private final PathMatcher matcher;
        private final AtomicInteger maxFiles;

        IndexConveyor ic = new IndexConveyor();

        public CustomFileVisitor(String mask, int maxFiles) {
            this.maxFiles = new AtomicInteger(maxFiles);
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + mask);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            boolean continue_scan = true;
            if (matcher.matches(file.getFileName())) {
                System.out.println("Found file: " + file);
                Parser p = new Parser(t -> {
                    ic.part().id(t.getToken()).label(IndexEvents.ADD_TOKEN).value(t).place();
                }, new FileStreamSupplier(file.toString()));
                p.parse();
                continue_scan = maxFiles.decrementAndGet() > 0 ;
            }
            return continue_scan ? FileVisitResult.CONTINUE : FileVisitResult.TERMINATE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println("Error visiting file: " + file + " - " + exc.getMessage());
            return FileVisitResult.CONTINUE;
        }
    }


}
package com.aegisql.search_engine;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FindAndIterateFilesTest {


    @Test
    public void test() {
        // Change the directory path to the root folder of your search
        Path directory = Path.of("/Volumes/SDCard/Books/gutenberg/");
        String mask = "*.txt"; // Change this to the file mask you want to search

        try {
            Files.walkFileTree(directory, new CustomFileVisitor(mask));
        } catch (IOException e) {
            System.err.println("Error searching files: " + e.getMessage());
        }
    }

    private static class CustomFileVisitor extends SimpleFileVisitor<Path> {
        private final PathMatcher matcher;

        public CustomFileVisitor(String mask) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + mask);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (matcher.matches(file.getFileName())) {
                System.out.println("Found file: " + file);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println("Error visiting file: " + file + " - " + exc.getMessage());
            return FileVisitResult.CONTINUE;
        }
    }
}


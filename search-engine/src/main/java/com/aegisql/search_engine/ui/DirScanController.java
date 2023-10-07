package com.aegisql.search_engine.ui;

import com.aegisql.search_engine.index.IndexConveyor;
import com.aegisql.search_engine.index.IndexEvents;
import com.aegisql.search_engine.parser.FileStreamSupplier;
import com.aegisql.search_engine.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class DirScanController {

    @Autowired
    IndexConveyor ic;

    @GetMapping("/performDirScan")
    @ResponseBody
    public List<String> performSearch(@RequestParam String path) {
        System.err.println("----- "+path);
        Path directory = Path.of(path);
        String mask = "*.txt";

        try {
            Files.walkFileTree(directory, new CustomFileVisitor(mask,100));
        } catch (IOException e) {
            System.err.println("Error searching files: " + e.getMessage());
        }

        return Arrays.asList("A","B");
    }

    private class CustomFileVisitor extends SimpleFileVisitor<Path> {
        private final PathMatcher matcher;
        private final AtomicInteger maxFiles;

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
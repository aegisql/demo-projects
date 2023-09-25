package com.aegisql.search_engine.utils;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileUtils {

    public static Stream<Character> toCharStream(@NotNull String file) {
        return toCharStream(file,StandardCharsets.UTF_8);
    }

    public static Stream<Character> toCharStream(@NotNull String file, Charset charset) {
        Path filePath = Path.of(file);
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            String text = new String(bytes,charset);
            return text.chars().mapToObj(c->(char)c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readAlignedFragment(Path filePath, long startPosition, int fragmentLength, int alignmentLength) {
        long size = -1;
        try {
             size = Files.size(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long lastByte = startPosition+fragmentLength;
        return readFragment(filePath,startPosition,fragmentLength);
    }

    public static String readFragment(Path filePath, long startPosition, int fragmentLength) {
        byte[] fragmentBytes = new byte[fragmentLength];
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath.toFile(), "r")) {
            randomAccessFile.seek(startPosition);
            randomAccessFile.read(fragmentBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(fragmentBytes);
    }

}

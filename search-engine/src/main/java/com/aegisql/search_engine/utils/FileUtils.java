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
import java.util.ArrayList;
import java.util.List;
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

    public static long fileSize(Path filePath) {
        long size = -1;
        try {
            size = Files.size(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return size;
    }

    public static String readAlignedFragment(Path filePath, long startPosition, int fragmentLength, int alignmentLength) {
        long size = fileSize(filePath);
        long lastByte = startPosition+fragmentLength;
        return readFragment(filePath,startPosition,fragmentLength,0);
    }

    public static String readFragment(Path filePath, long startPosition, int fragmentLength, int prefixLength) {
        long readFrom = Math.max(0L,startPosition-prefixLength);
        int readSize = fragmentLength + (int)(startPosition - readFrom);
        byte[] fragmentBytes = new byte[readSize];
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath.toFile(), "r")) {
            randomAccessFile.seek(readFrom);
            randomAccessFile.read(fragmentBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(fragmentBytes);
    }

    public static List<String> readFragments(Path filePath, List<Long> positions, int fragmentLength) {
        List<String> results = new ArrayList<>();
        long size = fileSize(filePath);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath.toFile(), "r")) {
            positions.stream().filter(pos -> pos < (size-1) ).forEach(pos->{
                int readBytes = (int) Math.min(size-pos,fragmentLength);
                try {
                    randomAccessFile.seek(pos);
                    byte[] fragmentBytes = new byte[readBytes];
                    randomAccessFile.read(fragmentBytes);
                    results.add(new String(fragmentBytes));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}

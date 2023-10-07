package com.aegisql.search_engine.parser;

import com.aegisql.search_engine.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Stream;

public class FileStreamSupplier implements CharacterStreamSupplier {

    private final String path;

    public FileStreamSupplier(@NotNull String path) {
        this.path = path;
    }
    @Override
    public Stream<Character> get() {
        return FileUtils.toCharStream(path);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileStreamSupplier that = (FileStreamSupplier) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileStreamSupplier{");
        sb.append("path='").append(path).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.aegisql.search_engine.parser;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Offset implements Comparable<Offset>{

    public final int absolute;
    public final int token;
    public Offset(int absolute, int token) {
        this.token = token;
        this.absolute = absolute;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offset{");
        sb.append("absolute=").append(absolute);
        sb.append(", token=").append(token);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offset offset = (Offset) o;
        return absolute == offset.absolute && token == offset.token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(absolute, token);
    }

    @Override
    public int compareTo(@NotNull Offset o) {
        return this.absolute - o.absolute;
    }
}

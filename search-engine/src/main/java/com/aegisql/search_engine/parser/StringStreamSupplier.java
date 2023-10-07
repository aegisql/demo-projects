package com.aegisql.search_engine.parser;

import java.util.Objects;
import java.util.stream.Stream;

public class StringStreamSupplier implements CharacterStreamSupplier{

    private final String txt;

    public StringStreamSupplier(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StringStreamSupplier{");
        sb.append("txt='").append(txt).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringStreamSupplier that = (StringStreamSupplier) o;
        return Objects.equals(txt, that.txt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(txt);
    }

    @Override
    public Stream<Character> get() {
        return txt.chars().mapToObj(c -> (char) c);
    }
}

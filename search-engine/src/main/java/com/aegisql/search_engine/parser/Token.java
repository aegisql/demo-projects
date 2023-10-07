package com.aegisql.search_engine.parser;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Token {
    private final Offset offset;
    @NotNull
    private final String token;

    private final CharacterStreamSupplier supplier;

    public Token(int absOffset, int tokenOffset, @NotNull String token, @NotNull CharacterStreamSupplier supplier) {
        this.offset = new Offset(absOffset,tokenOffset);
        this.token = token.toLowerCase();
        this.supplier = supplier;
    }

    public int getAbsOffset() {
        return offset.absolute;
    }

    public CharacterStreamSupplier getSupplier() {
        return supplier;
    }

    public int getTokenOffset() {
        return offset.token;
    }

    public Offset getOffset(){
        return offset;
    }

    public int getRelativeAbsOffset(Token other) {
        return this.offset.absolute - other.offset.absolute;
    }

    public int getRelativeTokenOffset(Token other) {
        return this.offset.token - other.offset.token;
    }

    public String getToken() {
        return token;
    }

    public int length() {
        return token.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(offset, token1.offset) && Objects.equals(token, token1.token) && Objects.equals(supplier, token1.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset, token, supplier);
    }

    public boolean contentEquals(String content) {
        return token.equalsIgnoreCase(content);
    }

    public boolean contentEquals(Token content) {
        if(content==null) return false;
        return contentEquals(content.getToken());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Token{");
        sb.append("'").append(token).append('\'');
        sb.append(", ").append(offset);
        sb.append(", ").append(supplier);
        sb.append('}');
        return sb.toString();
    }
}

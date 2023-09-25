package com.aegisql.search_engine.parser;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Token {
    private int absOffset;
    private int tokenOffset;
    @NotNull
    private String token;

    public Token(int absOffset, int tokenOffset, @NotNull String token) {
        this.absOffset = absOffset;
        this.tokenOffset = tokenOffset;
        this.token = token;
    }

    public int getAbsOffset() {
        return absOffset;
    }

    public int getTokenOffset() {
        return tokenOffset;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return absOffset == token1.absOffset && tokenOffset == token1.tokenOffset && token.equals(token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(absOffset, tokenOffset, token);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Token{");
        sb.append("absOffset=").append(absOffset);
        sb.append(", tokenOffset=").append(tokenOffset);
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

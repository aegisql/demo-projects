package com.aegisql.search_engine.parser;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class Parser {

    private int size = 0;

    private int tokenNumber = 0;

    private final Consumer<Token> tc;

    private final CharacterStreamSupplier supplier;

    boolean inSpace = true;

    public Parser(@NotNull Consumer<Token> tc, @NotNull CharacterStreamSupplier supplier) {
        this.supplier = supplier;
        this.tc = tc;
    }

    public void parse() {
        parse(supplier.get());
    }

    private void parse(Stream<Character> stream) {
        inSpace = true;
        final StringBuilder sb = new StringBuilder();
        stream.forEach((var next)->{
            if ( ! Character.isLetter(next)) {
                if(!inSpace) {
                    inSpace = true;
                    String token = sb.toString();
                    sb.setLength(0);
                    Token t = new Token(size-token.length(),tokenNumber++,token, supplier);
                    tc.accept(t);
                }
            } else {
                inSpace = false;
                sb.append(next);
            }
            size++;
        });
        if(!inSpace) {
            String token = sb.toString();
            Token t = new Token(size-token.length(),tokenNumber++,token, supplier);
            tc.accept(t);
        }
    }

    public int getSize() {
        return size;
    }

}

package com.aegisql.demo.builders.core;

import java.util.function.Function;

public interface ReturnControl<R> extends Function<Object,R> {
    static <R> ReturnControl <R> identity() {
        return x-> (R)x;
    }
}

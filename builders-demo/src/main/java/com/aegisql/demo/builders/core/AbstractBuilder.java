package com.aegisql.demo.builders.core;

import java.util.function.Supplier;

public abstract class AbstractBuilder <T, R> implements Supplier<T>, ModelPart<AbstractBuilder<T,R>> {

    protected T result;

    protected ReturnControl<R> returnControl;

    protected AbstractBuilder(ReturnControl<R> returnControl) {
        this.returnControl = returnControl;
    }

    protected final R done() {
        return returnControl.apply(this);
    }

    @Override
    public T get() {
        if(result == null) {
            result = build();
        }
        return result;
    }

    protected abstract T build();

}

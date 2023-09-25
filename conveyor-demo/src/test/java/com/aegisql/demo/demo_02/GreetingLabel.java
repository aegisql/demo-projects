package com.aegisql.demo.demo_02;

import com.aegisql.conveyor.SmartLabel;

import java.util.function.BiConsumer;

public enum GreetingLabel implements SmartLabel<GreetingBuilder> {
    GREETING(GreetingBuilder::greeting)
    ,NAME(GreetingBuilder::name);
    final BiConsumer<GreetingBuilder, Object> consumer;
    <T> GreetingLabel(BiConsumer<GreetingBuilder, T> consumer) {
        this.consumer = (BiConsumer<GreetingBuilder, Object>) consumer;
    }
    @Override
    public BiConsumer<GreetingBuilder, Object> get() {
        return consumer;
    }
}

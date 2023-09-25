package com.aegisql.demo.demo_09;

import com.aegisql.conveyor.SmartLabel;

import java.util.function.BiConsumer;

public enum SummaLabel implements SmartLabel<SummaBuilder> {
     ADD_VALUE(SummaBuilder::add) // Добавить значение
    ,DONE((b,v)->{/*do nothing*/}) // Закончить суммирование
    ;
    private final BiConsumer<SummaBuilder, Object> consumer;
    <T> SummaLabel(BiConsumer<SummaBuilder, T> consumer) {
        this.consumer = (BiConsumer<SummaBuilder, Object>) consumer;
    }
    @Override
    public BiConsumer<SummaBuilder, Object> get() {
        return consumer;
    }
}

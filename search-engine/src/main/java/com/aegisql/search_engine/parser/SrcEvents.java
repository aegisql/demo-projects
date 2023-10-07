package com.aegisql.search_engine.parser;

import com.aegisql.conveyor.SmartLabel;

import java.util.function.BiConsumer;

public enum SrcEvents implements SmartLabel<SourceProcessor> {
    PARSE_FILE(SourceProcessor::parseFile);

    BiConsumer<SourceProcessor, Object> consumer;

    <T> SrcEvents(BiConsumer<SourceProcessor, T> consumer) {
        this.consumer = (BiConsumer<SourceProcessor, Object>) consumer;
    }

    @Override
    public BiConsumer<SourceProcessor, Object> get() {
        return consumer;
    }
}

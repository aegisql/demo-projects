package com.aegisql.search_engine.search;

import com.aegisql.conveyor.SmartLabel;

import java.util.function.BiConsumer;

public enum SearchEvents implements SmartLabel<SearchResultBuilder> {
    INIT(SearchResultBuilder::init)
    ,TOKEN_FOUND(SearchResultBuilder::tokenFound)
    ;

    <T> SearchEvents(BiConsumer<SearchResultBuilder, T> consumer) {
        this.consumer = (BiConsumer<SearchResultBuilder, Object>) consumer;
    }

    BiConsumer<SearchResultBuilder, Object> consumer;

    @Override
    public BiConsumer<SearchResultBuilder, Object> get() {
        return consumer;
    }
}

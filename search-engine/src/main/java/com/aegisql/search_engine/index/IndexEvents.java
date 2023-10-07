package com.aegisql.search_engine.index;

import com.aegisql.conveyor.SmartLabel;
import com.aegisql.search_engine.doc.DocInfoBuilder;

import java.util.function.BiConsumer;

public enum IndexEvents implements SmartLabel<DocInfoBuilder> {

    ADD_TOKEN(DocInfoBuilder::addToken)
    ,FORGET_DOCUMENT(DocInfoBuilder::forgetDocument)
    ,COLLECT(DocInfoBuilder::collect)
    ,DONE(DocInfoBuilder::done)
    ;

    BiConsumer<DocInfoBuilder, Object> consumer;

    <T> IndexEvents(    BiConsumer<DocInfoBuilder, T> consumer) {
        this.consumer = (BiConsumer<DocInfoBuilder, Object>) consumer;
    }

    @Override
    public BiConsumer<DocInfoBuilder, Object> get() {
        return consumer;
    }
}

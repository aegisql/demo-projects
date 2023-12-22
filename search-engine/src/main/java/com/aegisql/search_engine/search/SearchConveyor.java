package com.aegisql.search_engine.search;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.consumers.result.IgnoreResult;
import com.aegisql.search_engine.search.SearchEvents;
import com.aegisql.search_engine.search.SearchResult;

public class SearchConveyor extends AssemblingConveyor<Long, SearchEvents, SearchResult> {

    public SearchConveyor() {
        this.setBuilderSupplier(SearchResultBuilder::new);
        this.setName("SearchConveyor");
        this.resultConsumer(new IgnoreResult<>()).set();//result delivered by future
        this.staticPart().label(SearchEvents.MAX_DOCS).value(10).place();
    }
}

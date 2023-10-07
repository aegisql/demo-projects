package com.aegisql.search_engine.search;

import com.aegisql.conveyor.Testing;
import com.aegisql.conveyor.TimeoutAction;
import com.aegisql.search_engine.index.LookupResult;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SearchResultBuilder implements Supplier<SearchResult>, Testing, TimeoutAction {
    private SearchQuery query;
    private boolean result;

    private List<LookupResult> results = new ArrayList<>();

    @Override
    public SearchResult get() {
        SearchResult searchResult = new SearchResult(results);
        return searchResult;
    }

    @Override
    public boolean test() {
        return result;
    }

    public void init(SearchQuery q) {
        this.query = q;
    }

    public void tokenFound(LookupResult res) {
        System.out.println(res);
        results.add(res);
    }

    @Override
    public void onTimeout() {
        result = results.size() > 0;
    }
}

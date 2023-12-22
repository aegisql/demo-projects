package com.aegisql.search_engine.search;

import com.aegisql.conveyor.Testing;
import com.aegisql.conveyor.TimeoutAction;
import com.aegisql.search_engine.index.LookupResult;
import com.aegisql.search_engine.parser.CharacterStreamSupplier;
import com.aegisql.search_engine.parser.Parser;
import com.aegisql.search_engine.parser.StringStreamSupplier;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SearchResultBuilder implements Supplier<SearchResult>, Testing, TimeoutAction {
    private SearchQuery query;
    private boolean result;

    private Set<CharacterStreamSupplier> sources = new HashSet<>();

    private List<LookupResult> results = new ArrayList<>();
    private int maxDocuments;

    int size = 0;

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
        Parser p = new Parser(token->{
            this.size++;
        }, new StringStreamSupplier(q.getToken())) ;
        p.parse();
    }

    public void tokenFound(LookupResult res) {
        System.out.println(res);
        sources.addAll(res.getFoundDoc().keySet());
        results.add(res);
        result = results.size() == size;
    }

    @Override
    public void onTimeout() {
        result = results.size() > 0;
    }

    public void maxDocuments(int max) {
        this.maxDocuments = max;
    }
}

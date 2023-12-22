package com.aegisql.search_engine.search;

import com.aegisql.search_engine.index.LookupResult;
import com.aegisql.search_engine.parser.CharacterStreamSupplier;
import com.aegisql.search_engine.parser.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SearchResult {

    private CharacterStreamSupplier source;

    private List<String> foundTokens = new ArrayList<>();

    private Set<Token> tokens = new TreeSet<>();

    private List<LookupResult> results;

    public SearchResult(List<LookupResult> results) {
        this.results = results;
    }

    public List<LookupResult> getResults() {
        return results;
    }

    public void setResults(List<LookupResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchResult{");
        sb.append("results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}

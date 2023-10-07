package com.aegisql.search_engine.search;

import com.aegisql.search_engine.index.LookupResult;

import java.util.List;

public class SearchResult {

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

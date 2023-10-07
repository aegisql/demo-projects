package com.aegisql.search_engine.search;

public class SearchQuery {
    private final String token;
    private final Long queryId;
    private final QueryType queryType;

    public SearchQuery(String token, Long queryId, QueryType queryType) {
        this.token = token;
        this.queryId = queryId;
        this.queryType = queryType;
    }

    public String getToken() {
        return token;
    }

    public Long getQueryId() {
        return queryId;
    }

    public QueryType getQueryType() {
        return queryType;
    }
}

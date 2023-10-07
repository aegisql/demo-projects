package com.aegisql.search_engine.ui;

import com.aegisql.id_builder.IdSource;
import com.aegisql.id_builder.impl.TimeHostIdGenerator;
import com.aegisql.search_engine.index.IndexConveyor;
import com.aegisql.search_engine.index.IndexEvents;
import com.aegisql.search_engine.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @Autowired
    SearchConveyor sc;

    @Autowired
    IndexConveyor ic;

    private final List<String> mockDatabase = Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape");

    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    public IdSource idSource = TimeHostIdGenerator.idGenerator_10x4x5(1000);

    @GetMapping("/performSearch")
    @ResponseBody
    public SearchResult performSearch(@RequestParam String query) {
        long nextId = idSource.getId();
        SearchQuery sq = new SearchQuery(query, nextId, QueryType.ANY);
        CompletableFuture<SearchResult> future = sc.future().id(nextId).ttl(Duration.ofSeconds(1)).get();
        sc.part().id(nextId).label(SearchEvents.INIT).value(sq).place();
        ic.part().id(query).label(IndexEvents.COLLECT).value(sq).place();
        SearchResult result = future.join();
        return result;
    }
}
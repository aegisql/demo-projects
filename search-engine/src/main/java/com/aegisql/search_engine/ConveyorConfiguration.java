package com.aegisql.search_engine;

import com.aegisql.search_engine.index.IndexConveyor;
import com.aegisql.search_engine.search.SearchConveyor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConveyorConfiguration {

    @Bean
    public SearchConveyor getSearchConveyor() {
        SearchConveyor sc = new SearchConveyor();
        return sc;
    }

    @Bean
    public IndexConveyor getIndexConveyor() {
        IndexConveyor ic = new IndexConveyor();
        return ic;
    }

}

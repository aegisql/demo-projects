package com.aegisql.search_engine.doc;

import com.aegisql.conveyor.Conveyor;
import com.aegisql.conveyor.Testing;
import com.aegisql.conveyor.loaders.PartLoader;
import com.aegisql.search_engine.index.LookupResult;
import com.aegisql.search_engine.parser.CharacterStreamSupplier;
import com.aegisql.search_engine.parser.Offset;
import com.aegisql.search_engine.parser.Token;
import com.aegisql.search_engine.search.SearchEvents;
import com.aegisql.search_engine.search.SearchQuery;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class DocInfoBuilder implements Supplier<DocInfo>, Testing {

    private String token;
    private final Map<CharacterStreamSupplier, List<Offset>> offsetMap = new HashMap<>();

    Supplier<PartLoader<Long, SearchEvents>> scs = PartLoader.lazySupplier("SearchConveyor");
    public void addToken(Token t) {
        if(token == null) {
            token = t.getToken();
        }
        List<Offset> offsets = offsetMap.computeIfAbsent(t.getSupplier(), s -> new LinkedList());
        offsets.add(t.getOffset());
    }

    public void collect(SearchQuery query) {
        LookupResult res = new LookupResult(token,offsetMap);
        scs.get().id(query.getQueryId()).label(SearchEvents.TOKEN_FOUND).value(res).place();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("token='").append(token).append('\'');
        offsetMap.forEach((sup,list)->{
            sb.append(", ").append(sup).append(" ").append(list.size()).append("\n");
        });
        sb.append('}');
        return sb.toString();
    }

    @Override
    public DocInfo get() {
        DocInfo docInfo = new DocInfo();
        return docInfo;
    }

    public <T> void done(T t) {
        System.out.println(this);
    }

    @Override
    public boolean test() {
        return offsetMap.size()==0;
    }

    public void forgetDocument(CharacterStreamSupplier t) {
        offsetMap.remove(t);
    }
}

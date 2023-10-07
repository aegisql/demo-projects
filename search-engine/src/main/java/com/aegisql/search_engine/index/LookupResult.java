package com.aegisql.search_engine.index;

import com.aegisql.search_engine.parser.CharacterStreamSupplier;
import com.aegisql.search_engine.parser.Offset;

import java.util.List;
import java.util.Map;

public class LookupResult {
    private final String token;
    private final Map<CharacterStreamSupplier, List<Offset>> foundDoc;

    public LookupResult(String token, Map<CharacterStreamSupplier, List<Offset>> offsetMap) {
        this.token = token;
        this.foundDoc = offsetMap;
    }

    public String getToken() {
        return token;
    }

    public Map<CharacterStreamSupplier, List<Offset>> getFoundDoc() {
        return foundDoc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LookupResult{");
        sb.append("token='").append(token).append('\'');
        sb.append(", foundDoc=").append(foundDoc);
        sb.append('}');
        return sb.toString();
    }
}

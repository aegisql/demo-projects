package com.aegisql.search_engine.index;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Conveyor;
import com.aegisql.search_engine.doc.DocInfo;
import com.aegisql.search_engine.doc.DocInfoBuilder;

public class IndexConveyor extends AssemblingConveyor<String,IndexEvents, DocInfo> {
    public IndexConveyor() {
        this.setBuilderSupplier(DocInfoBuilder::new);
        this.setName("IndexConveyor");
    }
}

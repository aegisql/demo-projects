package com.aegisql.search_engine.parser;

import com.aegisql.conveyor.AssemblingConveyor;

public class DocConveyor extends AssemblingConveyor<Integer,SrcEvents,Void> {

    public DocConveyor() {
        this.setName("DocConveyor");
        this.setBuilderSupplier(SourceProcessor::new);
    }

}

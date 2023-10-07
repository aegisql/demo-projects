package com.aegisql.search_engine.parser;

import com.aegisql.conveyor.Conveyor;
import com.aegisql.conveyor.Testing;
import com.aegisql.search_engine.index.IndexEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class SourceProcessor implements Supplier<Void>, Testing {
    private boolean ready;
    private static final Logger LOG = LoggerFactory.getLogger(SourceProcessor.class);

    private CharacterStreamSupplier characterStreamSupplier;

    Supplier<Conveyor> indexConveyorSupplier = Conveyor.lazySupplier("IndexConveyor");
    Supplier<Conveyor> searchConveyorSupplier = Conveyor.lazySupplier("SearchConveyor");

    public void parseFile(String path) {
        characterStreamSupplier = new FileStreamSupplier(path);
        LOG.debug("Parsing file {}",path);
        Parser p = new Parser(t->{
            indexConveyorSupplier.get().part().id(t.getToken()).label(IndexEvents.ADD_TOKEN).value(t).place();
        },characterStreamSupplier);
        p.parse();
    }

    @Override
    public Void get() {
        return (Void)null;
    }

    @Override
    public boolean test() {
        return ready;
    }
}

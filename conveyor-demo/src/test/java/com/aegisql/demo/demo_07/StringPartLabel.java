package com.aegisql.demo.demo_07;

import com.aegisql.conveyor.SmartLabel;

import java.util.function.BiConsumer;

public enum StringPartLabel implements SmartLabel<ConcatStringBuilder> {
    PART; // Только один тип блоков. Дифференциация производится самим строителем
    @Override
    public BiConsumer<ConcatStringBuilder, Object> get() {
        return (builder,part)->builder.stringPart(((String)part).trim());
    }
}

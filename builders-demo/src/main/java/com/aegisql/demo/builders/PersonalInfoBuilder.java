package com.aegisql.demo.builders;

import com.aegisql.demo.builders.core.AbstractBuilder;
import com.aegisql.demo.builders.core.ModelPartVisitor;
import com.aegisql.demo.models.PersonalInfo;

import java.util.function.Function;

public class PersonalInfoBuilder <R> extends AbstractBuilder<PersonalInfo,R> {

    protected PersonalInfoBuilder(Function<AbstractBuilder<PersonalInfo, R>, R> returnControl) {
        super(returnControl);
    }

    @Override
    protected PersonalInfo build() {
        return null;
    }

    @Override
    public AbstractBuilder<PersonalInfo, R> accept(ModelPartVisitor visitor) {
        return null;
    }
}

package com.aegisql.demo.builders.core;

import com.aegisql.demo.builders.AddressBuilder;
import com.aegisql.demo.builders.EmployerBuilder;
import com.aegisql.demo.builders.PersonalInfoBuilder;
import com.aegisql.demo.builders.PhoneBuilder;

public abstract class ModelPartVisitorAdapter implements ModelPartVisitor {

    @Override
    public <R> PhoneBuilder<R> visit(PhoneBuilder<R> builder) {
        return builder;
    }

    @Override
    public <R> AddressBuilder<R> visit(AddressBuilder<R> builder) {
        return builder;
    }

    @Override
    public <R> EmployerBuilder<R> visit(EmployerBuilder<R> builder) {
        return builder;
    }

    @Override
    public <R> PersonalInfoBuilder<R> visit(PersonalInfoBuilder<R> builder) {
        return builder;
    }
}

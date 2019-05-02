package com.aegisql.demo.builders.core;

import com.aegisql.demo.builders.AddressBuilder;
import com.aegisql.demo.builders.EmployerBuilder;
import com.aegisql.demo.builders.PhoneBuilder;

public interface ModelPartVisitor {
    <R> PhoneBuilder<R> visit(PhoneBuilder<R> builder);
    <R> AddressBuilder<R> visit(AddressBuilder<R> builder);
    <R> EmployerBuilder<R>  visit(EmployerBuilder<R> builder);
}

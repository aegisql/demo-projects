package com.aegisql.demo.builders.core;

import com.aegisql.demo.builders.AddressBuilder;
import com.aegisql.demo.builders.PhoneBuilder;

public interface ModelPartVisitor {
    <R> PhoneBuilder<R> visit(PhoneBuilder<R> phoneBuilder);
    <R> AddressBuilder<R> visit(AddressBuilder<R> addressBuilder);
}

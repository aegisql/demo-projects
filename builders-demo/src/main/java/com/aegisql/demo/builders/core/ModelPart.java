package com.aegisql.demo.builders.core;

public interface ModelPart <T> {

    T accept(ModelPartVisitor visitor);

}

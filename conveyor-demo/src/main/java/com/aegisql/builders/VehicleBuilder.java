package com.aegisql.builders;

import java.util.function.Supplier;

public interface  VehicleBuilder <T extends Vehicle> extends Supplier<T> {
}

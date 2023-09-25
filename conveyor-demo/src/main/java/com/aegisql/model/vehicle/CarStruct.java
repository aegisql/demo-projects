package com.aegisql.model.vehicle;

import java.util.Objects;

public class CarStruct extends VehicleStruct {
    int passengers;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarStruct{");
        sb.append("make='").append(make).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", year=").append(year);
        sb.append(", vin='").append(vin).append('\'');
        sb.append(", plateNumber='").append(plateNumber).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", passengers=").append(passengers);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarStruct carStruct = (CarStruct) o;
        return passengers == carStruct.passengers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengers);
    }
}

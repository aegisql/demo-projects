package com.aegisql.model.vehicle;

import java.util.Objects;

public class VehicleStruct {
    String make;
    String model;
    int year;
    String vin;
    String plateNumber;
    String color;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VehicleStruct{");
        sb.append("make='").append(make).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", year=").append(year);
        sb.append(", vin='").append(vin).append('\'');
        sb.append(", plateNumber='").append(plateNumber).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleStruct that = (VehicleStruct) o;
        return year == that.year && Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(vin, that.vin) && Objects.equals(plateNumber, that.plateNumber) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, vin, plateNumber, color);
    }
}

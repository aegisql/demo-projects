package com.aegisql.demo.models;

import java.util.Objects;

public final class Address {

    private final String streetNumber;
    private final String street;
    private final String aptNumber;
    private final String town;
    private final String state;
    private final String zipCode;

    public Address(String streetNumber, String street, String aptNumber, String town, String state, String zipCode) {
        this.streetNumber = streetNumber;
        this.street = street;
        this.aptNumber = aptNumber;
        this.town = town;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public String getTown() {
        return town;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetNumber, address.streetNumber) &&
                Objects.equals(street, address.street) &&
                Objects.equals(aptNumber, address.aptNumber) &&
                Objects.equals(town, address.town) &&
                Objects.equals(state, address.state) &&
                Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetNumber, street, aptNumber, town, state, zipCode);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("streetNumber='").append(streetNumber).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", aptNumber='").append(aptNumber).append('\'');
        sb.append(", town='").append(town).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.aegisql.demo.builders;

import com.aegisql.demo.builders.core.AbstractBuilder;
import com.aegisql.demo.builders.core.ModelPartVisitor;
import com.aegisql.demo.models.Address;

import java.util.function.Function;

public class AddressBuilder <R> extends AbstractBuilder<Address,R> {

    private final String streetNumber;
    private final String street;
    private final String aptNumber;
    private final String town;
    private final String state;
    private final String zipCode;

    public AddressBuilder(Function<AbstractBuilder<Address, R>, R> returnControl, String streetNumber, String street, String aptNumber, String town, String state, String zipCode) {
        super(returnControl);
        this.streetNumber = streetNumber;
        this.street = street;
        this.aptNumber = aptNumber;
        this.town = town;
        this.state = state;
        this.zipCode = zipCode;
    }

    protected AddressBuilder(Function<AbstractBuilder<Address, R>, R> returnControl) {
        this(returnControl,null,null,null,null,null,null);
    }

    public AddressBuilder <R> streetNumber(String sn) {
        return new AddressBuilder<>(returnControl,sn,street,aptNumber,town,state,zipCode);
    }

    public AddressBuilder <R> street(String st) {
        return new AddressBuilder<>(returnControl,streetNumber,st,aptNumber,town,state,zipCode);
    }

    public AddressBuilder <R> appartment(String apt) {
        return new AddressBuilder<>(returnControl,streetNumber,street,apt,town,state,zipCode);
    }

    public AddressBuilder <R> town(String twn) {
        return new AddressBuilder<>(returnControl,streetNumber,street,aptNumber,twn,state,zipCode);
    }

    public AddressBuilder <R> state(String st) {
        return new AddressBuilder<>(returnControl,streetNumber,street,aptNumber,town,st,zipCode);
    }

    public AddressBuilder <R> zipCode(String zip) {
        return new AddressBuilder<>(returnControl,streetNumber,street,aptNumber,town,state,zip);
    }

    @Override
    public AbstractBuilder<Address, R> accept(ModelPartVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Address build() {
        return new Address(streetNumber,street,aptNumber,town,state,zipCode);
    }

}

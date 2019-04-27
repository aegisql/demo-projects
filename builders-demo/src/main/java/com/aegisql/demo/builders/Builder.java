package com.aegisql.demo.builders;

import com.aegisql.demo.builders.PhoneBuilder;
import com.aegisql.demo.models.Address;
import com.aegisql.demo.models.Phone;

import java.util.function.Function;

public class Builder {

    public static PhoneBuilder<PhoneBuilder> ofPhone() {
        return new PhoneBuilder(a->a);
    }

    public static PhoneBuilder<PhoneBuilder> of(Phone phone) {
        return new PhoneBuilder(a->a,phone.getCountryCode(),phone.getAreaCode(),phone.getPhone(),phone.isPrimary(),phone.getPhoneType());
    }

    public static AddressBuilder<AddressBuilder> ofAddress() {
        return new AddressBuilder(a->a);
    }

    public static AddressBuilder<AddressBuilder> of(Address addr) {
        return new AddressBuilder(a->a,addr.getStreetNumber(),addr.getStreet(),addr.getAptNumber(),addr.getTown(),addr.getState(),addr.getZipCode());
    }

}

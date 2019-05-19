package com.aegisql.demo.simple;

import com.aegisql.demo.models.Phone;
import com.aegisql.demo.models.PhoneType;

import java.util.function.Supplier;

public class PhoneBuilder implements Supplier<Phone> {

    private int countryCode;
    private int areaCode;
    private int phone;
    private int ext;
    private boolean primary;
    private PhoneType phoneType;

    public PhoneBuilder() {

    }

    public PhoneBuilder(Phone p) {
        this.countryCode = p.getCountryCode();
        this.areaCode = p.getAreaCode();
        this.phone = p.getPhone();
        this.ext = p.getExt();
        this.primary = p.isPrimary();
        this.phoneType = p.getPhoneType();
    }


    public PhoneBuilder countryCode(int code) {
        this.countryCode = code;
        return this;
    }

    public PhoneBuilder areaCode(int code) {
        this.areaCode = code;
        return this;
    }

    public PhoneBuilder phone(int ph) {
        this.phone = ph;
        return this;
    }

    public PhoneBuilder ext(int ex) {
        this.ext = ex;
        return this;
    }

    public PhoneBuilder primary(boolean p) {
        this.primary = p;
        return this;
    }

    public PhoneBuilder phoneType(PhoneType type) {
        this.phoneType = type;
        return this;
    }

    @Override
    public Phone get() {
        return new Phone(countryCode,areaCode,phone,ext,primary,phoneType);
    }
}

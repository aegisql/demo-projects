package com.aegisql.demo.immutable;

import com.aegisql.demo.models.Phone;
import com.aegisql.demo.models.PhoneType;

import java.util.function.Supplier;

public class PhoneBuilder implements Supplier<Phone> {

    private final int countryCode;
    private final int areaCode;
    private final int phone;
    private final int ext;
    private final boolean primary;
    private final PhoneType phoneType;

    private Phone instance;

    private PhoneBuilder(int countryCode, int areaCode, int phone, int ext, boolean primary, PhoneType phoneType) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.phone = phone;
        this.ext = ext;
        this.primary = primary;
        this.phoneType = phoneType;
    }

    public PhoneBuilder() {
        this(0,0,0,0,false,null);
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
        return new PhoneBuilder(code,areaCode,phone,ext,primary,phoneType);
    }

    public PhoneBuilder areaCode(int code) {
        return new PhoneBuilder(countryCode,code,phone,ext,primary,phoneType);
    }

    public PhoneBuilder phone(int ph) {
        return new PhoneBuilder(countryCode,areaCode,ph,ext,primary,phoneType);
    }

    public PhoneBuilder ext(int ex) {
        return new PhoneBuilder(countryCode,areaCode,phone,ex,primary,phoneType);
    }

    public PhoneBuilder primary(boolean p) {
        return new PhoneBuilder(countryCode,areaCode,phone,ext,p,phoneType);
    }

    public PhoneBuilder phoneType(PhoneType type) {
        return new PhoneBuilder(countryCode,areaCode,phone,ext,primary,type);
    }

    @Override
    public Phone get() {
        if(instance == null) {
            instance = new Phone(countryCode,areaCode,phone,ext,primary,phoneType);
        }
        return instance;
    }
}

package com.aegisql.demo.builders;

import com.aegisql.demo.builders.core.AbstractBuilder;
import com.aegisql.demo.builders.core.ModelPartVisitor;
import com.aegisql.demo.models.Phone;
import com.aegisql.demo.models.PhoneType;

import java.util.function.Function;

public class PhoneBuilder<R> extends AbstractBuilder<Phone,R> {

    private final int countryCode;
    private final int areaCode;
    private final int phone;
    private final boolean primary;
    private final PhoneType phoneType;

    PhoneBuilder(Function<AbstractBuilder<Phone,R>,R> returnControl, int countryCode, int areaCode, int phone, boolean primary, PhoneType phoneType) {
        super((Function) returnControl);
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.phone = phone;
        this.primary = primary;
        this.phoneType = phoneType;
    }

    PhoneBuilder(Function<AbstractBuilder<Phone,R>, R> returnControl) {
        this(returnControl,1,0,0,false,null);
    }

    public PhoneBuilder<R> countryCode(int code) {
        return new PhoneBuilder<R>(returnControl,code,areaCode,phone,primary,phoneType);
    }

    public PhoneBuilder<R> areaCode(int code) {
        return new PhoneBuilder<R>(returnControl,countryCode,code,phone,primary,phoneType);
    }

    public PhoneBuilder<R> phone(int ph) {
        return new PhoneBuilder<R>(returnControl,countryCode,areaCode,ph,primary,phoneType);
    }

    public PhoneBuilder<R> primary(boolean p) {
        return new PhoneBuilder<R>(returnControl,countryCode,areaCode,phone,p,phoneType);
    }

    public PhoneBuilder<R> primary() {
        return primary(true);
    }

    public PhoneBuilder<R> phoneType(PhoneType type) {
        return new PhoneBuilder<R>(returnControl,countryCode,areaCode,phone,primary,type);
    }

    public PhoneBuilder<R> homePhone() {
        return phoneType(PhoneType.HOME);
    }

    public PhoneBuilder<R> workPhone() {
        return phoneType(PhoneType.WORK);
    }

    public PhoneBuilder<R> cellPhone() {
        return phoneType(PhoneType.CELL);
    }

    @Override
    public Phone build() {
        return new Phone(countryCode,areaCode,phone,primary,phoneType);
    }

    @Override
    public PhoneBuilder<R> accept(ModelPartVisitor visitor) {
        return visitor.visit(this);
    }

}

package com.aegisql.demo.builders;

import com.aegisql.demo.builders.core.AbstractBuilder;
import com.aegisql.demo.builders.core.ModelPartVisitor;
import com.aegisql.demo.builders.core.ReturnControl;
import com.aegisql.demo.models.Phone;
import com.aegisql.demo.models.PhoneType;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneBuilder<R> extends AbstractBuilder<Phone,R> {

    private final int countryCode;
    private final int areaCode;
    private final int phone;
    private final int ext;
    private final boolean primary;
    private final PhoneType phoneType;

    PhoneBuilder(ReturnControl<R> returnControl, int countryCode, int areaCode, int phone, int ext, boolean primary, PhoneType phoneType) {
        super(returnControl);
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.phone = phone;
        this.ext = ext;
        this.primary = primary;
        this.phoneType = phoneType;
    }

    PhoneBuilder(ReturnControl<R>  returnControl, Phone p) {
        this(returnControl,p.getCountryCode(),p.getAreaCode(),p.getPhone(),p.getExt(), p.isPrimary(),p.getPhoneType());
    }

    PhoneBuilder(ReturnControl<R> returnControl) {
        this(returnControl,1,0,0,0, false,null);
    }

    PhoneBuilder<R> returnTo(ReturnControl<R> returnControl) {
        return new PhoneBuilder<>(returnControl,countryCode,areaCode,phone,ext,primary,phoneType);
    }

    public PhoneBuilder<R> countryCode(int code) {
        return new PhoneBuilder<R>(returnControl,code,areaCode,phone,ext,primary,phoneType);
    }

    public PhoneBuilder<R> areaCode(int code) {
        return new PhoneBuilder<R>(returnControl,countryCode,code,phone,ext,primary,phoneType);
    }

    public PhoneBuilder<R> phone(int ph) {
        return new PhoneBuilder<R>(returnControl,countryCode,areaCode,ph,ext,primary,phoneType);
    }

    public PhoneBuilder<R> ext(int ex) {
        return new PhoneBuilder<R>(returnControl,countryCode,areaCode,phone,ex,primary,phoneType);
    }

    public PhoneBuilder<R> primary(boolean p) {
        return new PhoneBuilder<R>(returnControl,countryCode,areaCode,phone,ext,p,phoneType);
    }

    public PhoneBuilder<R> primary() {
        return primary(true);
    }

    public PhoneBuilder<R> phoneType(PhoneType type) {
        return new PhoneBuilder<R>(returnControl,countryCode,areaCode,phone,ext,primary,type);
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
        return new Phone(countryCode,areaCode,phone,ext,primary,phoneType);
    }

    @Override
    public PhoneBuilder<R> accept(ModelPartVisitor visitor) {
        return visitor.visit(this);
    }

    public R donePhone() {
        return done();
    }

    public static <R> List<PhoneBuilder<R>> convertList(ReturnControl<R>  returnControl, List<Phone> phones) {
        if(phones == null) {
            return null;
        } else {
            return phones.stream().map(p ->
                    new PhoneBuilder<R>(returnControl,p)).collect(Collectors.toList());
        }
    }

}

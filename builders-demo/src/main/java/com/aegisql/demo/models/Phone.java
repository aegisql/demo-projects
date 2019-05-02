package com.aegisql.demo.models;

import java.util.Objects;

public final class Phone {

    private final int countryCode;
    private final int areaCode;
    private final int phone;
    private final int ext;
    private final boolean primary;
    private final PhoneType phoneType;

    public Phone(int countryCode, int areaCode, int phone, int ext, boolean primary, PhoneType phoneType) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.phone = phone;
        this.ext = ext;
        this.primary = primary;
        this.phoneType = phoneType;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public int getPhone() {
        return phone;
    }

    public boolean isPrimary() {
        return primary;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public int getExt() {
        return ext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone1 = (Phone) o;
        return countryCode == phone1.countryCode &&
                areaCode == phone1.areaCode &&
                phone == phone1.phone &&
                ext == phone1.ext &&
                primary == phone1.primary &&
                phoneType == phone1.phoneType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, areaCode, phone, ext, primary, phoneType);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(phoneType)
                .append(" +")
                .append(countryCode)
                .append("-")
                .append(areaCode)
                .append("-")
                .append(phone)
                .insert(sb.length()-4,'-');
                if(ext > 0) sb.append(" ext.").append(ext);
                sb.append(primary?" primary":"");
        return sb.toString();
    }
}

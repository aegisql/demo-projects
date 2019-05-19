package com.aegisql.demo.immutable;


import com.aegisql.demo.models.Phone;
import com.aegisql.demo.models.PhoneType;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneBuilderTest {


    @Test
    public void testImmutableBuilder() {
        PhoneBuilder phoneBuilder = new PhoneBuilder()
                .countryCode(1)
                .areaCode(201)
                .phone(1112233)
                .ext(100)
                .primary(true)
                .phoneType(PhoneType.WORK);

        Phone phone = phoneBuilder.get();

        System.out.println(phone);
    }

    @Test
    public void prototypingTest() {

        PhoneBuilder US_PHONE_BUILDER = new PhoneBuilder().countryCode(1);
        PhoneBuilder RU_PHONE_BUILDER = new PhoneBuilder().countryCode(7);

        PhoneBuilder NYC_PHONE_BUILDER = US_PHONE_BUILDER.areaCode(212);
        PhoneBuilder SF_PHONE_BUILDER = US_PHONE_BUILDER.areaCode(415);
        PhoneBuilder MOS_PHONE_BUILDER = RU_PHONE_BUILDER.areaCode(495);

        Phone nyOffice = NYC_PHONE_BUILDER.phone(1234567).phoneType(PhoneType.WORK).primary(true).get();
        Phone sfOffice = SF_PHONE_BUILDER.phone(2345678).phoneType(PhoneType.WORK).primary(false).get();
        Phone mosOffice = MOS_PHONE_BUILDER.phone(1002030).phoneType(PhoneType.WORK).primary(false).get();
        System.out.println(nyOffice);
        System.out.println(sfOffice);
        System.out.println(mosOffice);



    }


}
package com.aegisql.demo.models;

import com.aegisql.demo.builders.Builder;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneTest {

    @Test
    public void testPhonePrintPrimary() {
        Phone p = new Phone(1,111,2223344,true,PhoneType.HOME);
        assertEquals("HOME +1-111-222-3344 primary",p.toString());
        System.out.println(p);
    }

    @Test
    public void testPhonePrint() {
        Phone p = new Phone(1,111,2223344,false,PhoneType.WORK);
        assertEquals("WORK +1-111-222-3344",p.toString());
        System.out.println(p);
    }

    @Test
    public void phoneBuilderBasicTest() {
        Phone phone = Builder.ofPhone()
                .countryCode(1)
                .areaCode(101)
                .phone(1112233)
                .primary()
                .cellPhone().get();

        assertEquals("CELL +1-101-111-2233 primary",phone.toString());
        System.out.println(phone);

        Phone phone2 = Builder.of(phone).get();
        assertEquals(phone,phone2);
        assertTrue( phone != phone2);
    }

}
package com.aegisql.demo.simple;

import com.aegisql.demo.models.Phone;
import com.aegisql.demo.models.PhoneType;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneBuilderTest {

    @Test
    public void testSimpleBuilder() {
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

}
package com.aegisql.demo.builders;

import com.aegisql.demo.models.Phone;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BuilderTest {

    @Test
    public void convertList() {

        PhoneBuilder<PhoneBuilder> pb = Builder.ofPhone();

        List<PhoneBuilder<PhoneBuilder>> pbl = Arrays.asList(pb);

        List<Phone> phones = Builder.convertList(pbl);
        assertNotNull(phones);
        assertEquals(1,phones.size());

    }
}
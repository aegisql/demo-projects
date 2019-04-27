package com.aegisql.demo.builders;

import com.aegisql.demo.models.Address;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressBuilderTest {

    @Test
    public void addressBuilderBasicTest() {
        Address address = Builder.ofAddress()
                .streetNumber("100")
                .street("Main")
                .appartment("2B")
                .town("Goodtown")
                .state("NJ")
                .zipCode("07000")
                .get();
        assertNotNull(address);
        System.out.println(address);
        Address address2 = Builder.of(address).get();
        assertEquals(address,address2);
        assertTrue(address != address2);
    }

}
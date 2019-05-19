package com.aegisql.demo.builders;

import com.aegisql.demo.models.Address;
import com.aegisql.demo.models.Employer;
import com.aegisql.demo.models.Phone;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployerBuilderTest {

    @Test
    public void basicEmployerBuilderTest() {
        Employer employer = Builder.ofEmployer()
                .companyName("test")
                .department("unitTest")
                .newAddress()
                    .streetNumber("1")
                    .street("main")
                    .town("Green")
                    .state("NJ")
                    .zipCode("07001")
                    .doneAddress()
                .phone()
                    .countryCode(1)
                    .areaCode(210)
                    .phone(1112233)
                    .workPhone()
                    .donePhone()
                .phone()
                    .countryCode(1)
                    .areaCode(210)
                    .phone(1112233)
                    .ext(100)
                    .workPhone()
                    .primary()
                    .donePhone()
                .get();
        System.out.println(employer);

        Address addr = employer.getAddress();
        Employer employer2 = Builder.of(employer)
                .address()
                    .street("central")
                    .doneAddress()
                .get();

        System.out.println(employer2);
        Employer employer3 = Builder.of(employer2)
                .editPhone(0)
                    .phone(1112200)
                    .donePhone()
                .build();

        System.out.println(employer3);

        Employer employer4 = Builder.of(employer3)
                .editPhone(pb->pb.get().isPrimary())
                    .ext(200)
                    .donePhone()
                .build();

        System.out.println(employer4);

        Employer employer5 = Builder.of(employer4)
                .deletePhone(pb-> ! pb.get().isPrimary())
                .build();

        System.out.println(employer5);

        Phone phone = Builder.of(employer4).editPhone(0).get();

        Employer employer6 = Builder.of(employer5)
                .phone(phone)
                .build();

        System.out.println(employer6);

    }

}
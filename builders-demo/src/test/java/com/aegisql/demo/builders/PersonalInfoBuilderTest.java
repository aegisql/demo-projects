package com.aegisql.demo.builders;

import com.aegisql.demo.models.PersonalInfo;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PersonalInfoBuilderTest {

    @Test
    public void basicPersonalInfoBuilderTest() {

        PersonalInfo personalInfo = Builder.ofPersonalInfo()
                .firstName("John")
                .middleName("X")
                .lastName("Doe")
                .male()
                .dateOfBirth(LocalDate.of(1999,01,10))
                .address()
                    .streetNumber("100")
                    .street("main")
                    .appartment("1B")
                    .town("Good")
                    .state("NJ")
                    .zipCode("07001")
                    .doneAddress()
                .employer()
                    .companyName("IBM")
                    .department("IT")
                    .phone()
                        .areaCode(123)
                        .phone(1112233)
                        .ext(444)
                        .donePhone()
                    .address()
                        .streetNumber("1")
                        .street("IBM Drive")
                        .town("Town")
                        .zipCode("10000")
                        .doneAddress()
                    .doneEmployer()

                .get();

        assertNotNull(personalInfo);
        System.out.println(personalInfo);

    }

}
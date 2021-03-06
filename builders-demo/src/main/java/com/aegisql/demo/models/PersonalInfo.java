package com.aegisql.demo.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PersonalInfo {

    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final Gender gender;
    private final LocalDate dateOfBirth;
    private final List<Phone> phones;
    private final Address address;
    private final Employer employer;
    private final PersonalInfo mother;
    private final PersonalInfo father;
    private final PersonalInfo spouse;
    private final List<PersonalInfo> siblings;
    private final List<PersonalInfo> children;
    private final Map<String,String> miscellaneous;


    public PersonalInfo(String firstName, String middleName, String lastName, Gender gender, LocalDate dateOfBirth,
                        List<Phone> phones, Address address, Employer employer,
                        PersonalInfo mother, PersonalInfo father, PersonalInfo spouse, List<PersonalInfo> siblings, List<PersonalInfo> children,
                        Map<String,String> miscellaneous) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phones = phones;
        this.address = address;
        this.employer = employer;
        this.mother = mother;
        this.spouse = spouse;
        this.father = father;
        this.siblings = siblings;
        this.children = children;
        this.miscellaneous = miscellaneous;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public Address getAddress() {
        return address;
    }

    public Employer getEmployer() {
        return employer;
    }

    public Map<String, String> getMiscellaneous() {
        return miscellaneous;
    }

    public PersonalInfo getMother() {
        return mother;
    }

    public PersonalInfo getFather() {
        return father;
    }

    public List<PersonalInfo> getSiblings() {
        return siblings;
    }

    public List<PersonalInfo> getChildren() {
        return children;
    }

    public PersonalInfo getSpouse() {
        return spouse;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonalInfo{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", phones=").append(phones);
        sb.append(", address=").append(address);
        sb.append(", employer=").append(employer);
        sb.append(", mother=").append(mother);
        sb.append(", father=").append(father);
        sb.append(", spouse=").append(spouse);
        sb.append(", siblings=").append(siblings);
        sb.append(", children=").append(children);
        sb.append(", miscellaneous=").append(miscellaneous);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalInfo that = (PersonalInfo) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName) &&
                gender == that.gender &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(phones, that.phones) &&
                Objects.equals(address, that.address) &&
                Objects.equals(employer, that.employer) &&
                Objects.equals(mother, that.mother) &&
                Objects.equals(father, that.father) &&
                Objects.equals(spouse, that.spouse) &&
                Objects.equals(siblings, that.siblings) &&
                Objects.equals(children, that.children) &&
                Objects.equals(miscellaneous, that.miscellaneous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, gender, dateOfBirth, phones, address, employer, mother, father, spouse, siblings, children, miscellaneous);
    }
}

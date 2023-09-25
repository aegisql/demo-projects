package com.aegisql.demo.models;

import java.util.List;
import java.util.Objects;

public final class Employer {

    private final String companyName;
    private final String department;
    private final Address address;
    private final List<Phone> phones;
    private final PersonalInfo reportsTo;

    public Employer(String companyName, String department, Address address, List<Phone> phones, PersonalInfo reportsTo) {
        this.companyName = companyName;
        this.department = department;
        this.address = address;
        this.phones = phones;
        this.reportsTo = reportsTo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDepartment() {
        return department;
    }

    public Address getAddress() {
        return address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public PersonalInfo getReportsTo() {
        return reportsTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(companyName, employer.companyName) &&
                Objects.equals(department, employer.department) &&
                Objects.equals(address, employer.address) &&
                Objects.equals(phones, employer.phones) &&
                Objects.equals(reportsTo, employer.reportsTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, department, address, phones, reportsTo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employer{");
        sb.append("companyName='").append(companyName).append('\'');
        sb.append(", department='").append(department).append('\'');
        sb.append(", address=").append(address);
        sb.append(", phones=").append(phones);
        sb.append(", reportsTo=").append(reportsTo);
        sb.append('}');
        return sb.toString();
    }

}

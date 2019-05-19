package com.aegisql.demo.builders;

import com.aegisql.demo.builders.core.AbstractBuilder;
import com.aegisql.demo.builders.core.ModelPartVisitor;
import com.aegisql.demo.builders.core.ReturnControl;
import com.aegisql.demo.models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonalInfoBuilder <R> extends AbstractBuilder<PersonalInfo,R> {

    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final Gender gender;
    private final LocalDate dateOfBirth;
    private final List<PhoneBuilder<PersonalInfoBuilder<R>>> phones;
    private final AddressBuilder<PersonalInfoBuilder<R>> address;
    private final EmployerBuilder<PersonalInfoBuilder<R>> employer;
    private final PersonalInfoBuilder<PersonalInfoBuilder<R>> mother;
    private final PersonalInfoBuilder<PersonalInfoBuilder<R>> father;
    private final PersonalInfoBuilder<PersonalInfoBuilder<R>> spouse;
    private final List<PersonalInfoBuilder<PersonalInfoBuilder<R>>> siblings;
    private final List<PersonalInfoBuilder<PersonalInfoBuilder<R>>> children;
    private final Map<String,String> miscellaneous;


    private PersonalInfoBuilder(ReturnControl<R> returnControl, String firstName, String middleName, String lastName, Gender gender, LocalDate dateOfBirth,
                        List<PhoneBuilder<PersonalInfoBuilder<R>>> phones, AddressBuilder<PersonalInfoBuilder<R>> address, EmployerBuilder<PersonalInfoBuilder<R>> employer,
                        PersonalInfoBuilder<PersonalInfoBuilder<R>> mother, PersonalInfoBuilder<PersonalInfoBuilder<R>> father, PersonalInfoBuilder<PersonalInfoBuilder<R>> spouse,
                                List<PersonalInfoBuilder<PersonalInfoBuilder<R>>> siblings, List<PersonalInfoBuilder<PersonalInfoBuilder<R>>> children,
                        Map<String,String> miscellaneous) {
        super(returnControl);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phones = phones;
        this.address = address;
        this.employer = employer;
        this.mother = mother;
        this.father = father;
        this.spouse = spouse;
        this.siblings = siblings;
        this.children = children;
        this.miscellaneous = miscellaneous;
    }

    protected PersonalInfoBuilder(ReturnControl<R> returnControl) {
        this(returnControl,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
    }

    protected PersonalInfoBuilder(ReturnControl<R>returnControl, PersonalInfo pi) {
        super(returnControl);
        this.firstName = pi.getFirstName();
        this.middleName = pi.getMiddleName();
        this.lastName = pi.getLastName();
        this.gender = pi.getGender();
        this.dateOfBirth = pi.getDateOfBirth();
        this.phones = PhoneBuilder.convertList(a->null,pi.getPhones());
        this.address = new AddressBuilder<>(a->null,pi.getAddress());
        this.employer = new EmployerBuilder<>(a->null,pi.getEmployer());
        this.mother = new PersonalInfoBuilder<>(a->null,pi.getMother());
        this.father = new PersonalInfoBuilder<>(a->null,pi.getFather());
        this.spouse = new PersonalInfoBuilder<>(a->null,pi.getSpouse());
        this.siblings = convertList(a->null,pi.getSiblings());
        this.children = convertList(a->null,pi.getChildren());
        this.miscellaneous = pi.getMiscellaneous();
    }

    public PersonalInfoBuilder<R> returnTo(ReturnControl<R> returnControl) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,address,employer,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> firstName(String firstName) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,address,employer,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> middleName(String middleName) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,address,employer,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> lastName(String lastName) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,address,employer,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> gender(Gender gender) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,address,employer,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> male() {
        return gender(Gender.MALE);
    }

    public PersonalInfoBuilder<R> female() {
        return gender(Gender.FEMALE);
    }

    public PersonalInfoBuilder<R> dateOfBirth(LocalDate dateOfBirth) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,address,employer,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> phoneBuilders(List<PhoneBuilder<PersonalInfoBuilder<R>>> phones) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,address,employer,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> phones(List<Phone> phones) {
        List<PhoneBuilder<PersonalInfoBuilder<R>>> pbList = PhoneBuilder.convertList(x->null,phones);
        return phoneBuilders(pbList);
    }
//////////////////

    //// ADDRESS
    private PersonalInfoBuilder<R> address(AddressBuilder<PersonalInfoBuilder<R>>  ab) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,ab,employer,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> address(Address  a) {
        return address(new AddressBuilder<>(b->null,a));
    }

    public AddressBuilder<PersonalInfoBuilder<R>> newAddress() {
        return new AddressBuilder<>(ab->this.address((AddressBuilder<PersonalInfoBuilder<R>>)ab));
    }

    public AddressBuilder<PersonalInfoBuilder<R>> address() {
        if(address == null) {
            return newAddress();
        } else {
            return address.returnTo(ab->this.address((AddressBuilder<PersonalInfoBuilder<R>>)ab));
        }
    }

    //// EMPLOYER
    public PersonalInfoBuilder<R> employer(EmployerBuilder  eb) {
        return new PersonalInfoBuilder<>(returnControl,firstName,middleName,lastName,gender,
                dateOfBirth,phones,address,eb,mother,father,spouse,siblings,children,miscellaneous);
    }

    public PersonalInfoBuilder<R> employer(Employer  e) {
        return employer(new EmployerBuilder<>(b->null,e));
    }

    public EmployerBuilder<PersonalInfoBuilder<R>> newEmployer() {
        return new EmployerBuilder<>(eb->this.employer((EmployerBuilder)eb));
    }

    public EmployerBuilder<PersonalInfoBuilder<R>> employer() {
        if(employer == null) {
            return newEmployer();
        } else {
            return employer.returnTo(eb->this.employer((EmployerBuilder)eb));
        }
    }

    @Override
    protected PersonalInfo build() {
        PersonalInfo pi = new PersonalInfo(firstName,middleName,lastName,gender,dateOfBirth,
                Builder.convertList(phones),Builder.get(address),Builder.get(employer),Builder.get(mother),Builder.get(father),Builder.get(spouse),
                Builder.convertList(siblings),Builder.convertList(children),miscellaneous);
        return pi;
    }

    @Override
    public AbstractBuilder<PersonalInfo, R> accept(ModelPartVisitor visitor) {
        return visitor.visit(this);
    }

    public static <R> List<PersonalInfoBuilder<R>> convertList(ReturnControl<R>  returnControl, List<PersonalInfo> infos) {
        if(infos == null) {
            return null;
        } else {
            return infos.stream().map(p ->
                    new PersonalInfoBuilder<R>(returnControl,p)).collect(Collectors.toList());
        }
    }

}

package com.aegisql.demo.builders;

import com.aegisql.demo.builders.core.AbstractBuilder;
import com.aegisql.demo.builders.core.ModelPartVisitor;
import com.aegisql.demo.builders.core.ReturnControl;
import com.aegisql.demo.models.Address;
import com.aegisql.demo.models.Employer;
import com.aegisql.demo.models.PersonalInfo;
import com.aegisql.demo.models.Phone;

import java.util.List;
import java.util.function.Predicate;

public class EmployerBuilder <R> extends AbstractBuilder<Employer, R> {

    private final String companyName;
    private final String department;
    private final AddressBuilder<EmployerBuilder<R>> addressBuilder;
    private final List<PhoneBuilder<EmployerBuilder<R>>> phoneBuilders;
    private final PersonalInfoBuilder<EmployerBuilder<R>> reportsTo;

    EmployerBuilder(ReturnControl<R> returnControl, String companyName, String department, AddressBuilder<EmployerBuilder<R>>  address, List<PhoneBuilder<EmployerBuilder<R>>> phones, PersonalInfoBuilder<EmployerBuilder<R>> reportsTo) {
        super(returnControl);
        this.companyName = companyName;
        this.department = department;
        this.addressBuilder = address;
        this.phoneBuilders = phones;
        this.reportsTo = reportsTo;
    }

    EmployerBuilder(ReturnControl<R>returnControl, Employer e) {
        this(returnControl,
                e.getCompanyName(),
                e.getDepartment(),
                new AddressBuilder(a->null,e.getAddress()),
                PhoneBuilder.convertList(a->null,e.getPhones()
                )
                ,new PersonalInfoBuilder<EmployerBuilder<R>>(a->null,e.getReportsTo())
        );
    }

    EmployerBuilder(ReturnControl<R> returnControl) {
        this(returnControl,null,null,null,null,null);
    }

    public EmployerBuilder<R> returnTo(ReturnControl<R> returnControl) {
        return new EmployerBuilder<>(returnControl,companyName,department,addressBuilder,phoneBuilders,reportsTo);
    }

    public EmployerBuilder<R> companyName(String name) {
        return new EmployerBuilder<>(returnControl,name,department,addressBuilder,phoneBuilders,reportsTo);
    }

    public EmployerBuilder<R> department(String depName) {
        return new EmployerBuilder<>(returnControl,companyName,depName,addressBuilder,phoneBuilders,reportsTo);
    }

    public EmployerBuilder<R> address(AddressBuilder ab) {
        return new EmployerBuilder<R>(returnControl,companyName,department,ab,phoneBuilders,reportsTo);
    }

    public AddressBuilder<EmployerBuilder<R>> address() {
        if(addressBuilder == null) {
            return newAddress();
        } else {
            return addressBuilder.returnTo(ab->this.address((AddressBuilder) ab));
        }
    }

    public AddressBuilder<EmployerBuilder<R>> newAddress() {
        return new AddressBuilder<>(
                ab -> this.address((AddressBuilder)ab)
        );
    }

    public AddressBuilder<EmployerBuilder<R>> address(Address a) {
        return new AddressBuilder<>(
                ab -> this.address((AddressBuilder)ab), a
        );
    }

    public EmployerBuilder<R> reportTo(PersonalInfoBuilder pib) {
        return new EmployerBuilder<R>(returnControl,companyName,department,addressBuilder,phoneBuilders,pib);
    }

    public PersonalInfoBuilder<EmployerBuilder<R>> reportTo() {
        return new PersonalInfoBuilder<EmployerBuilder<R>>(pib->this.reportTo((PersonalInfoBuilder)pib)).employer(this);
    }

    public PersonalInfoBuilder<EmployerBuilder<R>> reportTo(PersonalInfo pi) {
        return new PersonalInfoBuilder<EmployerBuilder<R>>(pib->this.reportTo((PersonalInfoBuilder)pib), pi).employer(this);
    }

    public PhoneBuilder<EmployerBuilder<R>> phone() {
        return new PhoneBuilder<>(pb->{
            List<PhoneBuilder<EmployerBuilder<R>>> newBuilderList = Builder.appendToList(phoneBuilders,(PhoneBuilder<EmployerBuilder<R>>)pb);
            return new EmployerBuilder<>(this.returnControl,this.companyName,this.department,this.addressBuilder,newBuilderList,reportsTo);
        });
    }

    public EmployerBuilder<R> phone(Phone ph) {
        PhoneBuilder<EmployerBuilder<R>> pb = new PhoneBuilder<>(b->this,ph);
        List<PhoneBuilder<EmployerBuilder<R>>> newBuilderList = Builder.appendToList(phoneBuilders, pb);
        return new EmployerBuilder<>(this.returnControl,this.companyName,this.department,this.addressBuilder,newBuilderList,reportsTo);
    }

    public EmployerBuilder<R> phone(int i, Phone ph) {
        PhoneBuilder<EmployerBuilder<R>> pb = new PhoneBuilder<>(b->this,ph);
        List<PhoneBuilder<EmployerBuilder<R>>> newBuilderList = Builder.replaceItem(phoneBuilders, i, pb);
        return new EmployerBuilder<>(this.returnControl,this.companyName,this.department,this.addressBuilder,newBuilderList,reportsTo);
    }

    public EmployerBuilder<R> phones(List<Phone> phones) {
        List<PhoneBuilder<EmployerBuilder<R>>> newPhoneBuilders = PhoneBuilder.convertList(b->this, phones);
        return new EmployerBuilder<>(returnControl,companyName,department,addressBuilder,newPhoneBuilders,reportsTo);
    }

    public PhoneBuilder<EmployerBuilder<R>> editPhone(int i) {
        return phoneBuilders.get(i).returnTo(pb->
                new EmployerBuilder<R>(returnControl,companyName,department,addressBuilder,
                        Builder.replaceItem(phoneBuilders,i,(PhoneBuilder<EmployerBuilder<R>> )pb),reportsTo));
    }

    public PhoneBuilder<EmployerBuilder<R>> editPhone(Predicate<PhoneBuilder<EmployerBuilder<R>>> filter) {
        for(int i = 0; i < phoneBuilders.size(); i++) {
            PhoneBuilder<EmployerBuilder<R>> next = phoneBuilders.get(i);
            final int pos = i;
            if (filter.test(next)) {
                return next.returnTo(pb -> new EmployerBuilder<R>(returnControl, companyName, department, addressBuilder,
                        Builder.replaceItem(phoneBuilders, pos, (PhoneBuilder<EmployerBuilder<R>>) pb),reportsTo));
            }
        }
        return null;
    }

    public EmployerBuilder<R> deletePhone(int pos) {
        List<PhoneBuilder<EmployerBuilder<R>>> newPhoneBuilders = Builder.deleteItem(phoneBuilders,pos);
        return new EmployerBuilder<>(returnControl,companyName,department,addressBuilder,newPhoneBuilders,reportsTo);
    }

    public EmployerBuilder<R> deletePhone(Predicate<PhoneBuilder<EmployerBuilder<R>>> filter) {
        List<PhoneBuilder<EmployerBuilder<R>>> newPhoneBuilders = Builder.deleteItem(phoneBuilders,filter);
        return new EmployerBuilder<>(returnControl,companyName,department,addressBuilder,newPhoneBuilders,reportsTo);
    }

    @Override
    protected Employer build() {
        return new Employer(companyName,department,Builder.get(addressBuilder),Builder.convertList(phoneBuilders),Builder.get(reportsTo));
    }

    @Override
    public AbstractBuilder<Employer, R> accept(ModelPartVisitor visitor) {
        return visitor.visit(this);
    }

    public R doneEmployer() {
        return done();
    }

}

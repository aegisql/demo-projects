package com.aegisql.demo.builders;

import com.aegisql.demo.builders.core.ReturnControl;
import com.aegisql.demo.models.Address;
import com.aegisql.demo.models.Employer;
import com.aegisql.demo.models.PersonalInfo;
import com.aegisql.demo.models.Phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Builder {

    public static PhoneBuilder<PhoneBuilder> ofPhone() {
        return new PhoneBuilder(ReturnControl.identity());
    }

    public static AddressBuilder<AddressBuilder> ofAddress() {
        return new AddressBuilder(ReturnControl.identity());
    }

    public static EmployerBuilder<EmployerBuilder> ofEmployer() {
        return new EmployerBuilder(ReturnControl.identity());
    }

    public static PersonalInfoBuilder<PersonalInfoBuilder> ofPersonalInfo() {
        return new PersonalInfoBuilder(ReturnControl.identity());
    }

    public static AddressBuilder<AddressBuilder> of(Address addr) {
        return new AddressBuilder(
                 ReturnControl.identity(), addr);
    }

    public static PhoneBuilder<PhoneBuilder> of(Phone phone) {
        return new PhoneBuilder(
                ReturnControl.identity(), phone);
    }

    public static EmployerBuilder<EmployerBuilder> of(Employer employer) {
        return new EmployerBuilder(
                ReturnControl.identity(), employer);
    }

    public static PersonalInfoBuilder<PersonalInfoBuilder> of(PersonalInfo personalInfo) {
        return new PersonalInfoBuilder(
                ReturnControl.identity(), personalInfo);
    }

    public static <T> List<T> convertList(List<? extends Supplier<T>> builders) {
        if(builders == null) {
            return null;
        } else {
            return builders.stream().map(Supplier::get).collect(collectingAndThen(toList(),
                    Collections::unmodifiableList));
        }
    }

    public static <T> List<T> appendToList(List<T> list, T item) {
        List<T> newList = list == null ? new ArrayList<>() : new ArrayList<>(list);
        newList.add(item);
        return newList;
    }

    public static <T> List<T> replaceItem(List<T> list, int i, T item) {
        List<T> newList = list == null ? new ArrayList<>() : new ArrayList<>(list);
        newList.set(i,item);
        return newList;
    }

    public static <T> List<T> deleteItem(List<T> list, int pos) {
        List<T> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            if(i==pos) continue;
            newList.add(list.get(i));
        }
        return newList;
    }

    public static <T> List<T> deleteItem(List<T> list, Predicate<T> filter) {
        List<T> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            if(filter.test(list.get(i))) continue;
            newList.add(list.get(i));
        }
        return newList;
    }

    public static <T> T get(Supplier<T> sup) {
        return sup == null ? null : sup.get();
    }

}

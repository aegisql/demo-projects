package com.aegisql.demo.builders;

import com.aegisql.demo.builders.PhoneBuilder;
import com.aegisql.demo.models.Address;
import com.aegisql.demo.models.Employer;
import com.aegisql.demo.models.Phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Builder {

    public static PhoneBuilder<PhoneBuilder> ofPhone() {
        return new PhoneBuilder(a->a);
    }

    public static AddressBuilder<AddressBuilder> ofAddress() {
        return new AddressBuilder(a->a);
    }

    public static EmployerBuilder<EmployerBuilder> ofEmployer() {
        return new EmployerBuilder(a->a);
    }

    public static AddressBuilder<AddressBuilder> of(Address addr) {
        return new AddressBuilder(
                 Function.identity(), addr);
    }

    public static PhoneBuilder<PhoneBuilder> of(Phone phone) {
        return new PhoneBuilder(
                 Function.identity(), phone);
    }

    public static EmployerBuilder<EmployerBuilder> of(Employer employer) {
        return new EmployerBuilder(
                Function.identity(), employer);
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

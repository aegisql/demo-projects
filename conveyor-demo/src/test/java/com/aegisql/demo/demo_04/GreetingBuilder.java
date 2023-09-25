package com.aegisql.demo.demo_04;

import java.util.function.Supplier;

public class GreetingBuilder implements Supplier<String> {
    // Сохраняем строительные блоки в соответствующих полях
    private String greeting;
    private String name;

    // Метод-фабрика для создания продукта
    @Override
    public String get() {
        return greeting + ", " + name + "!";
    }
    // Сеттер для приветствия
    public void greeting(String value) {
        requireNonBlank(value,"Greeting is blank");
        greeting = value;
    }
    // Сеттер для имени
    public void name(String value) {
        requireNonBlank(value,"Name is blank");
        name = value;
    }
    // Простой валидатор проверяет, что строки содержат не пустое значение
    private static void requireNonBlank(String value, String message) {
        if(value.isBlank()) throw new RuntimeException(message);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GreetingBuilder{");
        sb.append("greeting='").append(greeting).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

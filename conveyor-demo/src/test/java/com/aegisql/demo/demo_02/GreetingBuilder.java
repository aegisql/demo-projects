package com.aegisql.demo.demo_02;

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
        greeting = value;
    }
    // Сеттер для имени
    public void name(String value) {
        name = value;
    }

}

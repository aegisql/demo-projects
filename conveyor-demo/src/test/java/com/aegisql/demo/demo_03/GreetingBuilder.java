package com.aegisql.demo.demo_03;

import java.util.function.Supplier;

public class GreetingBuilder implements Supplier<String> {
    // Сохраняем строительные блоки в соответствующих полях
    private String greeting;
    private String name;
    private String spacer = " ";
    private String eol = ".";

    // Метод-фабрика для создания продукта
    @Override
    public String get() {
        return greeting + spacer + name + eol;
    }
    // Сеттер для приветствия
    public void greeting(String value) {
        greeting = value;
    }
    // Сеттер для имени
    public void name(String value) {
        name = value;
    }
    public void spacer(String spacer) {
        this.spacer = spacer;
    }
    public void eol(String eol) {
        this.eol = eol;
    }
}

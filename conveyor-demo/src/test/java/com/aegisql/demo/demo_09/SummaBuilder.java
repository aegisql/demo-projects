package com.aegisql.demo.demo_09;

import java.util.function.Supplier;

public class SummaBuilder implements Supplier<Double> {
    // Храним сумму
    private double summa = 0;
    // Возвращаем сумму
    @Override
    public Double get() {
        return summa;
    }
    // Добавляем значение
    public void add(Double x) {
        summa += x;
    }
}

package com.aegisql.demo.demo_01;

import java.util.function.Supplier;

public class SummaBuilder implements Supplier<Integer> {

    // Сохраняем строительные блоки в соответствующих полях
    private int partA;
    private int partB;

    // Метод-фабрика для создания продукта
    @Override
    public Integer get() {
        return partA+partB;
    }

    // Часть А имеет строковое представление и требует парсинга
    public void partA(String value) {
        partA = Integer.parseInt(value);
    }

    // Часть Б не требует никакой специальной обработки
    public void partB(Integer value) {
        partB = value;
    }

}

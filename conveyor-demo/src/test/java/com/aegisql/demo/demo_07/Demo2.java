package com.aegisql.demo.demo_07;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Priority;

import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

import static com.aegisql.conveyor.Priority.PRIORITIZED;
import static com.aegisql.demo.demo_07.StringPartLabel.PART;

public class Demo2 {

    public static void main(String[] args) {
        // Создаем конвейер
        AssemblingConveyor<String,StringPartLabel,String> stringConcatenator = new AssemblingConveyor<>(PriorityBlockingQueue::new);
        // Даем конвейеру имя
        stringConcatenator.setName("string_concatenator");
        // Задаем метод создания строителя
        stringConcatenator.setBuilderSupplier(ConcatStringBuilder::new);
        // Обработчик ошибок
        stringConcatenator.scrapConsumer(bin->System.err.print(bin)).set();
        // Обработчик продукта
        stringConcatenator.resultConsumer(bin->System.out.print(bin.product)).set();
        // Разрешаем динамически сдвигать время таймаута
        stringConcatenator.enablePostponeExpiration(true);
        // Разрешаем динамически сдвигать время таймаута если таймаут уже произошел
        stringConcatenator.enablePostponeExpirationOnTimeout(true);
        // Приостановить конвейер
        stringConcatenator.suspend();
        // Помещаем данные в очередь в обратном порядке, но с приоритетами
        stringConcatenator.part().id("test").value("three").priority(1).label(PART).place();
        stringConcatenator.part().id("test").value("two").priority(2).label(PART).place();
        stringConcatenator.part().id("test").value("one").priority(3).label(PART).place();
        // Восстанавливаем чтение из очереди
        stringConcatenator.resume();
        // Завершаем ввод
        stringConcatenator.part().id("test").value("").priority(0).label(PART).place();
        // Ждем окончания обработки
        stringConcatenator.completeAndStop().join();
    }

}

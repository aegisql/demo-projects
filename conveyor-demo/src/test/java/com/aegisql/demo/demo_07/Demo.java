package com.aegisql.demo.demo_07;

import com.aegisql.conveyor.AssemblingConveyor;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static com.aegisql.demo.demo_07.StringPartLabel.PART;

public class Demo {

    public static void main(String[] args) {
        // Будем читать строки из стандартного ввода
        Scanner scanner = new Scanner(System.in);
        // Создаем конвейер
        AssemblingConveyor<String,StringPartLabel,String> stringConcatenator = new AssemblingConveyor<>();
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
        // Читаем ввод
        String nextLine = "next";
        while ( true ) {
            // Получаем строку
            nextLine = scanner.nextLine();
            // Посылаем конвейеру
            stringConcatenator.part().id("test").value(nextLine).label(PART).place();
            // Обработаем только одну серию строк. Достаточно для теста
            if(nextLine.isBlank()) break;
        }
        // Подождем окончания обработки
        stringConcatenator.completeAndStop().join();
    }

}

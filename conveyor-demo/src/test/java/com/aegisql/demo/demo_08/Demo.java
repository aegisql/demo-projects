package com.aegisql.demo.demo_08;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.cart.Cart;
import com.aegisql.conveyor.persistence.core.PersistentConveyor;
import com.aegisql.conveyor.persistence.jdbc.JdbcPersistence;
import com.aegisql.conveyor.persistence.jdbc.builders.JdbcPersistenceBuilder;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static com.aegisql.demo.demo_08.StringPartLabel.PART;

public class Demo {

    public static void main(String[] args) throws Exception {
        // Будем читать строки из стандартного ввода
        Scanner scanner = new Scanner(System.in);
        // Контейнер для correlationId
        AtomicInteger correlationId = new AtomicInteger(0);
        // Создаем персистенс для временного хранения частей
        JdbcPersistence<Integer> persistence = JdbcPersistenceBuilder
                .presetInitializer("sqlite", Integer.class) // на базе SQLITE
                .database("string_parts") // имя базы данных
                .autoInit(true) // создать БД и необходимые таблицы и индексы автоматически
                .setArchived() // при архивации устанавливать флаг ARCHIVED
                .maxBatchSize(1) // Частота работы архиватора для теста должна быть максимальной
                .labelConverter(StringPartLabel.class) // конвертер в/из строки для бирок
                .build(); // создать персистенс
        // Создаем конвейер
        AssemblingConveyor<Integer, StringPartLabel,String> c = new AssemblingConveyor<>();
        // Задаем метод создания строителя
        c.setBuilderSupplier(ConcatStringBuilder::new);
        // Обработчик ошибок - по окончании увеличиваем на единицу correlationId
        c.scrapConsumer(bin->System.err.print(bin)).andThen(bin->correlationId.incrementAndGet()).set();
        // Обработчик продукта - по окончании увеличиваем на единицу correlationId
        c.resultConsumer(bin->System.out.print(bin.product)).andThen(bin->correlationId.incrementAndGet()).set();
        // Разрешаем динамически сдвигать время таймаута
        c.enablePostponeExpiration(true);
        // Разрешаем динамически сввигать время таймаута если таймаут уже произошел
        c.enablePostponeExpirationOnTimeout(true);
        // Создаем PersistentConveyor
        PersistentConveyor<Integer, StringPartLabel, String> stringConcatenator = persistence.wrapConveyor(c);
        // Даем имя конвейеру
        stringConcatenator.setName("string_concatenator");

        // Ищем один из сохраненных correlationId
        Integer lastKey = persistence.getAllParts().stream().map(Cart::getKey).findFirst().orElse(1);
        // Устанавливаем текущий correlationId
        correlationId.set(lastKey);
        // Читаем ввод
        String nextLine = "next";
        while ( true ) {
            // Получаем строку
            nextLine = scanner.nextLine();
            // Посылаем конвейеру - возвращаемое Future возвращает true после успешной обработки очередного блока
            CompletableFuture<Boolean> future = stringConcatenator.part().id(correlationId.get()).value(nextLine).label(PART).place();
            // Обработаем только одну серию строк. Достаточно для теста
            if(nextLine.isBlank()) {
                try {
                    Boolean dataProcessed = future.join(); // Подождем окончания обработки последнего блока
                    if (dataProcessed) {
                        System.out.println("Conveyor processed data");
                        break; // Выход
                    } else {
                        System.err.println("Conveyor rejected data");
                        throw new RuntimeException("Conveyor rejected data");
                    }
                } catch (RuntimeException e) {
                    System.err.println("Conveyor failed processing data. "+e.getMessage());
                }
            }
        }
        // Безусловная остановка конвейера.
        // Все незавершенные сборки будут переданы обработчику остатков
        stringConcatenator.stop();
    }

}

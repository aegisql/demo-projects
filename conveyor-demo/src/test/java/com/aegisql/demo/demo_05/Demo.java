package com.aegisql.demo.demo_05;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Conveyor;
import com.aegisql.conveyor.loaders.BuilderLoader;
import org.junit.Test;

import java.time.*;
import java.util.Objects;
import java.util.UUID;

import static com.aegisql.demo.demo_05.GreetingLabel.*;
import static org.junit.Assert.assertEquals;

public class Demo {

    public static void main(String[] args) {
        // Создаем экземпляр конвейера
        Conveyor<UUID, GreetingLabel, String> greetingAggregator = new AssemblingConveyor<>();
        // Ошибки выводим в стандартный поток ошибок
        greetingAggregator.scrapConsumer(bin->System.err.println(bin)).set();
        // Продукт выводим в стандартный поток вывода
        greetingAggregator.resultConsumer(bin->System.out.println(bin.product)).set();
        // Задаем предикат готовности
        greetingAggregator.setReadinessEvaluator(Conveyor.getTesterFor(greetingAggregator).accepted(GREETING,NAME));
        // Тестируем:
        // Используем случайный CORRELATION_ID
        UUID correlationID = UUID.randomUUID();
        // Создаем экземпляр загрузчика строителя. Он может быть использован повторно
        var builderLoader = greetingAggregator
                .build()
                .supplier(GreetingBuilder::new)
                .ttl(Duration.ofSeconds(3));
        // Первым посылаем запрос создать экземпляр
        builderLoader.id(correlationID).create();
        // Посылаем данные. Порядок не важен
        greetingAggregator
                .part()
                .id(correlationID)
                .label(GREETING)
                .value("Hello")
                .ttl(Duration.ofSeconds(3))
                .place();


        // Вычисляем начало следующего дня
        Instant midnight = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant();
        // Сборка завершится в полночь
        greetingAggregator
                .part()
                .id(correlationID)
                .label(GREETING)
                .value("Hello")
                .expirationTime(midnight)
                .place();

        System.err.println(midnight);

        greetingAggregator.part().id(correlationID).label(NAME).value("World").place();
        builderLoader.id(correlationID).create();
        greetingAggregator.part().id(correlationID).label(GREETING).value("Hi").place();
        greetingAggregator.part().id(correlationID).label(CANCEL).value("for the demo").place();
        greetingAggregator.part().id(correlationID).label(NAME).value("Planet").place();
        // Ждем завершения всех активных сборок и останавливаем конвейер
        greetingAggregator.completeAndStop().join();
    }

    @Test
    public void testMain() {
        main(new String[]{"",""});
    }

    @Test
    public void testSummaBuilder() {
        GreetingBuilder summaBuilder = new GreetingBuilder();
        summaBuilder.greeting("Hello");
        summaBuilder.name("World");
        String greeting = summaBuilder.get();
        assertEquals("Expected 'Hello, World!'","Hello, World!",greeting);
    }

}

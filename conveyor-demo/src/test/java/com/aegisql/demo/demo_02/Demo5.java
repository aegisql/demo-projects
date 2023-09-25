package com.aegisql.demo.demo_02;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Conveyor;
import com.aegisql.conveyor.consumers.result.IgnoreResult;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.aegisql.demo.demo_02.GreetingLabel.GREETING;
import static com.aegisql.demo.demo_02.GreetingLabel.NAME;
import static org.junit.Assert.assertEquals;

public class Demo5 {

    public static void main(String[] args) {
        // Создаем экземпляр конвейера
        Conveyor<UUID, GreetingLabel, String> greetingAggregator = new AssemblingConveyor<>();
        // Продукт будем получать через Future
        greetingAggregator.resultConsumer(IgnoreResult.of(greetingAggregator)).set();
        // Задаем предикат готовности
        greetingAggregator.setReadinessEvaluator(Conveyor.getTesterFor(greetingAggregator).accepted(GREETING,NAME));
        // Тестируем:
        // Используем случайный CORRELATION_ID
        UUID correlationID = UUID.randomUUID();
        // Инициируем агрегацию
        greetingAggregator.build().id(correlationID).supplier(GreetingBuilder::new).create();
        // Запрашиваем будущее
        CompletableFuture<String> greetingFuture = greetingAggregator.future().id(correlationID).get();
        // Посылаем данные. Порядок не важен
        greetingAggregator.part().id(correlationID).label(GREETING).value("Hello").place();
        greetingAggregator.part().id(correlationID).label(NAME).value("World").place();
        // Ждем результат
        String greeting = greetingFuture.join();
        // Используем
        System.out.println(greeting);
        // Останавливаем конвейер
        greetingAggregator.stop();
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

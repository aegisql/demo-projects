package com.aegisql.demo.demo_02;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Conveyor;
import org.junit.Test;

import java.util.UUID;

import static com.aegisql.demo.demo_02.GreetingLabel.GREETING;
import static com.aegisql.demo.demo_02.GreetingLabel.NAME;
import static org.junit.Assert.assertEquals;

public class Demo4 {

    public static void main(String[] args) {
        // Создаем экземпляр конвейера
        Conveyor<UUID, GreetingLabel, String> greetingAggregator = new AssemblingConveyor<>();
        // Конвейер создает экземпляр строителя с помощью конструктора
        greetingAggregator.setBuilderSupplier(GreetingBuilder::new);
        // Продукт выводим в стандартный поток вывода
        greetingAggregator.resultConsumer(bin->System.out.println(bin.product)).set();
        // Задаем предикат готовности
        greetingAggregator.setReadinessEvaluator(Conveyor.getTesterFor(greetingAggregator).accepted(GREETING,NAME));
        // Тестируем:
        // Используем случайный CORRELATION_ID
        UUID correlationID = UUID.randomUUID();
        // Посылаем данные. Порядок не важен
        greetingAggregator.part().id(correlationID).label(GREETING).value("Hello").place();
        greetingAggregator.part().id(correlationID).label(NAME).value("World").place();
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

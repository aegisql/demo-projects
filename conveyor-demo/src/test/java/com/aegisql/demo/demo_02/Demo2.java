package com.aegisql.demo.demo_02;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Conveyor;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class Demo2 {

    public static void main(String[] args) {
        // Создаем экземпляр конвейера
        Conveyor<Integer, String, String> greetingAggregator = new AssemblingConveyor<>();
        // Конвейер создает экземпляр строителя с помощью конструктора
        greetingAggregator.setBuilderSupplier(GreetingBuilder::new);
        // Продукт выводим в стандартный поток вывода
        greetingAggregator.resultConsumer(bin->System.out.println(bin.product)).set();
        // Связываем бирки с методами строителя
        greetingAggregator.setDefaultCartConsumer(Conveyor.getConsumerFor(greetingAggregator, GreetingBuilder.class)
                .when("greeting", GreetingBuilder::greeting)
                .when("name", GreetingBuilder::name)
        );
        // Задаем предикат готовности
        greetingAggregator.setReadinessEvaluator(Conveyor.getTesterFor(greetingAggregator).accepted("greeting","name"));

        // Тестируем:
        // Используем случайный CORRELATION_ID
        Integer correlationID = 1;
        // Посылаем данные. Порядок не важен
        greetingAggregator.part().id(correlationID).label("greeting").value("Hello").place();
        greetingAggregator.part().id(correlationID).label("name").value("World").place();
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

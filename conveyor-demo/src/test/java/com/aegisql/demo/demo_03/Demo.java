package com.aegisql.demo.demo_03;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Conveyor;
import org.junit.Test;

import java.util.Objects;
import java.util.UUID;

import static com.aegisql.demo.demo_03.GreetingLabel.*;
import static org.junit.Assert.assertEquals;

public class Demo {

    public static void main(String[] args) {
        // Создаем экземпляр конвейера
        Conveyor<UUID, GreetingLabel, String> greetingAggregator = new AssemblingConveyor<>();
        // Конвейер создает экземпляр строителя с помощью конструктора
        greetingAggregator.setBuilderSupplier(GreetingBuilder::new);

        // Альтернативный способ инициализации строителя
        greetingAggregator.setBuilderSupplier(()->{
            GreetingBuilder greetingBuilder = new GreetingBuilder();
            greetingBuilder.spacer(", ");
            greetingBuilder.eol("!");
            return greetingBuilder;
        });

        // Продукт выводим в стандартный поток вывода вместе с е-мэйлом и сабжектом
        greetingAggregator.resultConsumer(bin-> {
            String eMail = (String) bin.properties.get("E-MAIL");
            Objects.requireNonNull(eMail,"Expected e-mail");
            String subject = (String) bin.properties.getOrDefault("SUBJECT","Greetings");
            System.out.println("MailTo: "+eMail);
            System.out.println("Subject: "+subject);
            System.out.println(bin.product);
        }).set();
        // Задаем предикат готовности
        greetingAggregator.setReadinessEvaluator(Conveyor.getTesterFor(greetingAggregator).accepted(GREETING, NAME, SPACER, EOL));
        // Посылаем статические данные для пробела и окончания строки
        // Они будут применяться ко всем агрегатам.
        greetingAggregator.staticPart().label(SPACER).value(",\n").place();
        greetingAggregator.staticPart().label(EOL).value(";").place();

        // Тестируем:
        // Используем случайный CORRELATION_ID
        UUID correlationID = UUID.randomUUID();
        // В начале посылаем строки для пробела и окончания приветствия
        greetingAggregator.part().id(correlationID).label(SPACER).value(", ")
                .addProperty("E-MAIL","mail@user.info")
                .addProperty("SUBJECT","Greetings from RACE").place();
        greetingAggregator.part().id(correlationID).label(EOL).value("!").place();
        // Затем приветствие и имя
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

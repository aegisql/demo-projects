package com.aegisql.demo.demo_09;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Conveyor;
import com.aegisql.conveyor.persistence.core.PersistentConveyor;
import com.aegisql.conveyor.persistence.jdbc.JdbcPersistence;
import com.aegisql.conveyor.persistence.jdbc.builders.JdbcPersistenceBuilder;
import com.aegisql.conveyor.validation.CommonValidators;
import com.aegisql.demo.demo_08.StringPartLabel;

import java.util.concurrent.CompletableFuture;

import static com.aegisql.demo.demo_09.DuplicatesFilter.VALUE_ID;
import static com.aegisql.demo.demo_09.SummaLabel.ADD_VALUE;
import static com.aegisql.demo.demo_09.SummaLabel.DONE;

public class Demo2 {
    public static void main(String[] args) throws Exception {
        JdbcPersistence<Integer> persistence = JdbcPersistenceBuilder
                .presetInitializer("sqlite", Integer.class) // на базе SQLITE
                .database("summa_values") // имя базы данных
                .autoInit(true) // создать БД и необходимые таблицы и индексы автоматически
                .deleteArchiving() // при архивации удалять все записи
                .maxBatchSize(1) // Частота работы архиватора для теста должна быть максимальной
                .labelConverter(SummaLabel.class) // конвертер в/из строки для бирок
                .addField(Integer.class,VALUE_ID) // Добавляем колонку для хранения параметра VALUE_ID
                .addUniqueFields(VALUE_ID) // Устанавливаем требование уникальности для VALUE_ID
                .build(); // создать персистенс
        persistence.archiveAll(); // для теста начинаем с чистой БД
        // Создаем конвейер
        AssemblingConveyor<Integer,SummaLabel,Double> summator = new AssemblingConveyor<>();
        // оборачиваем конвейер в персистенс
        var persistentSummator = persistence.wrapConveyor(summator);
        // Устанавливаем имя
        persistentSummator.setName("summator");
        // Метод создания строителя
        persistentSummator.setBuilderSupplier(SummaBuilder::new);
        // Обработчик продукта
        persistentSummator.resultConsumer(bin->System.out.println("Summa["+bin.key+"] = "+bin.product)).set();
        // Обработчик ошибок
        persistentSummator.scrapConsumer(bin->System.err.println("Error key="+bin.key+": "+bin.error)).set();
        // Добавляем фильтр не пустых значений
        persistentSummator.addCartBeforePlacementValidator(CommonValidators.CART_VALUE_NOT_NULL());
        // Заканчиваем когда получили бирку DONE
        persistentSummator.setReadinessEvaluator(Conveyor.getTesterFor(summator).accepted(DONE));
        // Используем загрузчики
        var loader = persistentSummator.part().label(ADD_VALUE);
        var done = persistentSummator.part().label(DONE).value(0);

        // Данные помеченные VALUE_ID будут проверены на наличие дубликатов перед сохранением в БД
        loader.id(1).value(0.1).addProperty(VALUE_ID,10001).place();
        loader.id(1).value(0.2).addProperty(VALUE_ID,10002).place();
        // Дубликат будет отвергнут до записи в БД установленным уникальным индексом
        loader.id(1).value(0.2).addProperty(VALUE_ID, 10002).place();
        loader.id(1).value(0.3).addProperty(VALUE_ID,10003).place();
        // Ждем окончания. Требование уникального VALUE_ID распространяется на все бирки и диктуется схемой БД
        done.id(1).addProperty(VALUE_ID,10004).place().join();
        // Останавливаем конвейер
        persistentSummator.stop();
    }
}

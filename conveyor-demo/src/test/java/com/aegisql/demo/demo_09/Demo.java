package com.aegisql.demo.demo_09;

import com.aegisql.conveyor.AssemblingConveyor;
import com.aegisql.conveyor.Conveyor;
import com.aegisql.conveyor.ProductBin;
import com.aegisql.conveyor.loaders.PartLoader;
import com.aegisql.conveyor.validation.CommonValidators;

import static com.aegisql.demo.demo_09.DuplicatesFilter.VALUE_ID;
import static com.aegisql.demo.demo_09.SummaLabel.ADD_VALUE;
import static com.aegisql.demo.demo_09.SummaLabel.DONE;

public class Demo {
    public static void main(String[] args) {
        // Создаем экземпляр фильтра
        DuplicatesFilter filter = new DuplicatesFilter();
        // Создаем конвейер
        AssemblingConveyor<Integer,SummaLabel,Double> summator = new AssemblingConveyor<>();
        // Устанавливаем имя
        summator.setName("summator");
        // Метод создания строителя
        summator.setBuilderSupplier(SummaBuilder::new);
        // Обработчик продукта
        summator.resultConsumer(bin->System.out.println("Summa["+bin.key+"] = "+bin.product)).set();
        // Обработчик ошибок
        summator.scrapConsumer(bin->System.err.println(bin)).set();
        // Добавляем фильтр не пустых значений
        summator.addCartBeforePlacementValidator(CommonValidators.CART_VALUE_NOT_NULL());
        // Добавляем фильтр дубликатов
        summator.addCartBeforePlacementValidator(filter.getValidator());
        // Добавляем уведомление по окончании агрегации
        summator.addBeforeKeyEvictionAction(filter.getAcknowledge());
        // Заканчиваем когда получили бирку DONE
        summator.setReadinessEvaluator(Conveyor.getTesterFor(summator).accepted(DONE));
        // Используем загрузчики
        var loader = summator.part().label(ADD_VALUE);
        var done = summator.part().label(DONE).value(0);
        // Посылаем null
        loader.id(1).value(null).place();
        // Посылаем данные с дубликатом без VALUE_ID
        loader.id(1).value(0.1).place();
        loader.id(1).value(0.2).place();
        loader.id(1).value(0.2).place(); // дубликат будет просуммирован
        loader.id(1).value(0.3).place();
        done.id(1).place();
        // Данные помеченные VALUE_ID будут проверены на наличие дубликатов
        loader.id(2).value(0.1).addProperty(VALUE_ID,10001).place();
        loader.id(2).value(0.2).addProperty(VALUE_ID,10002).place();
        loader.id(2).value(0.2).addProperty(VALUE_ID,10002).place(); // дубликат будет отвергнут фильтром

        var peekValue = summator.command().id(2).peek().join();
        System.out.println("Текущее значение суммы для key="+peekValue.key+": "+peekValue.product);
        Math.abs(-0.0);

        loader.id(2).value(0.3).addProperty(VALUE_ID,10003).place();
        done.id(2).place().join();
        // Останавливаем конвейер
        summator.stop();
    }
}

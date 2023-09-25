package com.aegisql.demo.demo_09;

import com.aegisql.conveyor.AcknowledgeStatus;
import com.aegisql.conveyor.cart.Cart;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class DuplicatesFilter {
    // Имя параметра используемое загрузчиком
    public static String VALUE_ID = "VALUE_ID";
    // Храним уникальные VALUE_ID для каждого CORRELATION_ID
    private Map<Integer, Set<Integer>> valueIds = new HashMap<>();
    // Валидатор
    public Consumer<Cart<Integer, ?, SummaLabel>> getValidator() {
        return cart->{
            Integer valueId = cart.getProperty(VALUE_ID, Integer.class);
            if(valueId == null) {
                return; // Игнорируем данные не помеченные VALUE_ID
            }
            Integer key = cart.getKey(); // CORRELATION_ID
            Set<Integer> ids = valueIds.computeIfAbsent(key, k -> new HashSet<>());
            if(ids.contains(valueId)) { // Дубликат обнаружен
                throw new RuntimeException("Duplicate found for "+key+"#"+valueId+" value="+cart.getValue());
            } else { // Сохранить VALUE_ID
                ids.add(valueId);
            }
        };
    }
    // Действие после завершения суммирования
    public Consumer<AcknowledgeStatus<Integer>> getAcknowledge() {
        return status->{
            // Суммирование завершено. Удаляем накопленные VALUE_IDs для данного CORRELATION_ID
            valueIds.remove(status.getKey());
        };
    }
}

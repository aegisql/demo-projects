package com.aegisql.demo.demo_08;

import com.aegisql.conveyor.Expireable;
import com.aegisql.conveyor.Testing;
import com.aegisql.conveyor.TimeoutAction;

import java.util.function.Supplier;

public class ConcatStringBuilder implements Supplier<String>, TimeoutAction, Testing, Expireable {
    // Накапливаем результат в буфере
    StringBuilder sb = new StringBuilder();
    // Состояние готовности
    boolean ready = false;
    long timestamp;
    // Время таймаута
    private long expirationTime = System.currentTimeMillis() + 5000;
    // Фабрика продукта
    @Override
    public String get() {
        return sb.toString();
    }
    // Сеттер для частей
    public void stringPart(String part) {
        // Согласно требованием, пустая строка является признаком окончания ввода
        if(part.isEmpty()) {
            // Готово
            ready = true;
        } else {
            // Добавляем пробел
            if (!sb.isEmpty()) {
                sb.append(" ");
            }
            // Присоединяем часть
            sb.append(part);
        }
        // Сдвигаем время таймаута на 5 секунд в будущее
        expirationTime = System.currentTimeMillis() + 5000;
    }
    // Метод интерфейса TimeoutAction
    @Override
    public void onTimeout() {
        // Вычисляем задержку
        long delta = System.currentTimeMillis() - expirationTime;
        if(delta > 0) { // Истинный таймаут
            // Готово если есть накопленные строки
            ready = ! sb.isEmpty();
        } else { // expirationTime успел обновиться раньше срабатывания таймера
            // ждем еще delta миллисекунд
            expirationTime = System.currentTimeMillis() - delta;
        }
    }
    // Предикат готовности, метод интерфейса Testing
    @Override
    public boolean test() {
        return ready;
    }
    // метод интерфейса Expireable возвращает время наступления таймаута
    @Override
    public long getExpirationTime() {
        return expirationTime;
    }
}

package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс описывает модель очереди со свойствами <b>in</b> и <b>out</b>
 * В классе используется модель стека {@link SimpleStack}
 *
 * @param <T> - элемент
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SimpleQueue<T> {
    /**
     * Поле основной стек
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    /**
     * Поле стек, который используется для удаления элементов
     */
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод используется для удаления первого элемента в очереди
     * Использует внутри методы {@link SimpleStack#push(Object)} и {@link SimpleStack#pop()}
     * 1) Проверяем не пустой ли {@link SimpleQueue#in}, если он пуст, выкидываем {@link NoSuchElementException}
     * 2) Если {@link SimpleQueue#out} пуст, то перекидываем в него все элементы из {@link SimpleQueue#in}
     * 3) Удаляем и возвращаем последний элемент из {@link SimpleQueue#out}
     *
     * @return - возвращает удаленный элемент
     */
    public T poll() {
        if (in.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод используется для вставки элемента в конец очереди стека {@link SimpleQueue#out}
     * Использует внутри метод {@link SimpleStack#push(Object)}
     *
     * @param value - элемент
     */
    public void push(T value) {
        in.push(value);
    }
}

package ru.job4j.collection;

/**
 * Класс описывает модель реализации Stack со свойством <b>linked</b>
 * Класс основан на базе связного списка {@link ForwardLinked}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public boolean isEmpty() {
        return linked.isEmpty();
    }
}

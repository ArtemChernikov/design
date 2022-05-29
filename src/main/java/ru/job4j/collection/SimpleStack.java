package ru.job4j.collection;

/**
 * Класс описывает модель реализации Stack со свойством <b>linked</b>
 * Класс основан на базе связного списка {@link ForwardLinked}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SimpleStack<T> {
    /**
     * Поле связного списка
     */
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * Метод используется для удаления из стэка
     * Использует внутри {@link ForwardLinked#deleteFirst()}
     *
     * @return - возвращает удаленный элемент
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод используется для добавление элемента в стэк
     * Использует внутри {@link ForwardLinked#addFirst(Object)}
     *
     * @param value - добавляемый элемент
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}

package ru.job4j.collection;

/**
 * Класс описывает модель реализации Stack со свойством <b>linked</b>
 * Класс основан на базе связного списка {@link ForwardLinked}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class SimpleStack<T> {
    /**
     * Поле связный список
     */
    private final ForwardLinked<T> linked = new ForwardLinked<>();
    /**
     * Поле размер стека
     */
    private int size = 0;

    /**
     * Метод используется для удаления последнего элемента из стека
     * Использует внутри {@link ForwardLinked#deleteFirst()}
     *
     * @return - возвращает удаленный элемент
     */
    public T pop() {
        T rsl = linked.deleteFirst();
        size--;
        return rsl;
    }

    /**
     * Метод используется для добавления элемента в стек
     * Использует внутри {@link ForwardLinked#addFirst(Object)}
     *
     * @param value - добавляемый элемент
     */
    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    /**
     * Метод используется для проверки пуст ли стек или нет
     *
     * @return - возвращает true - если стек пуст, false - если наоборот
     */
    public boolean isEmpty() {
        return size == 0;
    }
}

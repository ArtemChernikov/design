package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс описывает модель коллекции LinkedList со свойством <b>head</b>
 * Класс реализовывает интерфейс {@link Iterable}
 * В классе используется вложенный класс {@link ForwardLinked.Node}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ForwardLinked<T> implements Iterable<T> {
    /**
     * Поле первого узла коллекции
     */
    private Node<T> head;

    /**
     * Метод используется для добавления узла в коллекцию
     * 1) Если коллекция пустая, присваиваем {@link ForwardLinked#head}
     * новый узел с элементом и с ссылкой на следующий узел - null
     * 2) Создаем локальную переменную последнего узла и присваиваем ей узел {@link ForwardLinked#head}
     * 3) Запсукаем цикл пока {@link ForwardLinked#head}.next != null (добираемся до последнего узла коллекции)
     * 4) Присваиваем новый узел следующему узлу в хвосте коллекции
     *
     * @param value - элемент
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод используется для добавление узла на первое место в коллекции
     * 1) Проверяется пустая коллекция или нет, если пустая, присваиваем {@link ForwardLinked#head} новый узел
     * 2) Если коллекция не пустая, присваиваем новый узел, где ссылкой на следующий узел будет, старый первый узел
     *
     * @param value - добавляемый элемент
     */
    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            return;
        }
        head = new Node<>(value, head);
    }

    /**
     * Метод используется для удаления первого узла колеекции
     * 1) Создаем локальную переменную rsl и присваиваем ей первый узел
     * 2) Если rsl == null, то выбрасываем {@link NoSuchElementException}
     * 3) Присваиваем {@link ForwardLinked#head} узел, который идет после head
     * 4) Обнуляем ссылку на следующий узел и элемент старого первого узла
     *
     * @return - возвращает элемент удаленного узла
     */
    public T deleteFirst() {
        final Node<T> node = head;
        if (node == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.value;
        head = node.next;
        node.next = null;
        node.value = null;
        return rsl;
    }

    /**
     * Анонимный класс {@link Iterator} для корректного перебора элементов коллекции
     *
     * @return - возвращает итератор
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            /**
             * Поле для указателя узла, на котором сейчас находится итератор
             */
            Node<T> node = head;

            /**
             * Метод позволяет узнать, есть ли следующий элемент в коллекции
             * 1) Проверяем равен ли указатель узла null
             * @return - true - если следующий элемент есть, false - если нет
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * Метод используется для передвижения указателя итератора по коллекции
             * и возврата соответствующего значения
             * 1) Проверяем методом {@link Iterator#hasNext()} есть ли следующий элемент в коллекции
             * если нет, то выбрасывается {@link NoSuchElementException}
             * 2) Присваиваем локальной переменной элемент узла коллекции
             * 3) Переводим указатель на следующий элемент
             * 4) Возвращаем переменную с элементом
             *
             * @return - возвращаем следующий элемент коллекции
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Приватный статический вложенный класс для обозначения
     * узлов в коллекции со свойствами <b>value</b> и <b>next</b>
     * Каждый узел имеет в себе сам элемент типа {@link T}
     * и следующий узел типа {@link ForwardLinked.Node}
     *
     * @param <T> - тип элемента
     */
    private static class Node<T> {
        /**
         * Поле элемента узла
         */
        T value;
        /**
         * Поле следующего узла типа {@link ForwardLinked.Node}
         */
        Node<T> next;

        /**
         * Конструктор - создание нового узла с определенными значениями
         *
         * @param value - элемент
         * @param next  - следующий узел
         */
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

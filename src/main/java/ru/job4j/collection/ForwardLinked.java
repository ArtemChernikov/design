package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс описывает модель коллекции LinkedList со свойством <b>head</b>
 * Класс реализовывает интерфейс {@link Iterable}
 * В классе используется вложенный класс {@link ForwardLinked.Node}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.2
 */
public class ForwardLinked<T> implements Iterable<T> {
    /**
     * Поле первый узел коллекции
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
     *
     * @param value - добавляемый элемент
     */
    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    /**
     * Метод используется для удаления первого узла коллекции
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
     * Метод используется для перестановки элементов связного списка в обратном порядке
     * 1) Проверяем сколько элементов в коллекции, если 0 или 1, то перестановка бессмысленна
     * 2) Если в коллекции больше одного элемента:
     * 3) Создаем локальную переменную current (текущий) - она будет отвечать за текущий элемент
     * 4) Создаем локальную переменную temp (временный) - она будет отвечать за перестановку ссылок в элементах
     * 5) Запускаем цикл пока current элемент не равен null (он будет равен null, когда мы дойдем до конца)
     * 5.1 Создаем локальную переменную next (следующий) - она отвечает за следующий элемент после текущего
     * 5.2 Присваиваем current элементу ссылку current.next на следующий элемент temp
     * 5.3 Присваиваем temp элементу значение текущего элемента, для передачи ссылки в след. итерации
     * 5.4 Присваиваем current элементу значение next следующего элемента, для перехода на другой
     * элемент в следующей итерации
     * 5.5 И так пока не дойдем до конца коллекции (пока current элемент не будет равен null
     * и temp не будет равен последнему элементу)
     * 6) Присваиваем {@link ForwardLinked#head} значение temp
     *
     * @return - возвращает boolean true - если в коллекции успешно призведен ревёрс, false - если нет
     */
    public boolean revert() {
        boolean rsl = false;
        if (head != null && head.next != null) {
            Node<T> current = head;
            Node<T> temp = null;
            while (current != null) {
                Node<T> next = current.next;
                current.next = temp;
                temp = current;
                current = next;
            }
            head = temp;
            rsl = true;
        }
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
             * Поле указатель узла, на котором сейчас находится итератор
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

        @Override
        public String toString() {
            return "Node{"
                    + "value=" + value
                    + ", next=" + next
                    + '}';
        }
    }
}

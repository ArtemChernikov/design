package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс описывает модель коллекции LinkedList со свойствами <b>head</b>, <b>tail</b>, <b>size</b> и <b>modCount</b>
 * Класс реализовывает интерфейс {@link LinkedList}
 * В классе используется вложенный класс {@link Node}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SimpleLinkedList<E> implements LinkedList<E> {
    /**
     * Приватный статический вложенный класс для обозначения
     * узлов в коллекции со свойствами <b>item</b>, <b>next</b> и <b>prev</b>
     * Каждый узел имеет в себе ссылку на предыдущий узел типа {@link Node},
     * сам элемент типа {@link E} и следующий узел типа {@link Node}
     *
     * @param <E> - тип элемента
     */
    private static class Node<E> {
        /**
         * Поле элемента узла
         */
        E item;
        /**
         * Поле следующего узла типа {@link Node}
         */
        Node<E> next;
        /**
         * Поле предыдущего узла типа {@link Node}
         */
        Node<E> prev;

        /**
         * Конструктор - создание нового узла с определенными значениями
         *
         * @param prev    - предыдущий узел
         * @param element - элемент
         * @param next    - следующий узел
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Поле первого узла коллекции
     */
    private Node<E> head;
    /**
     * Поле последнего узла коллекции
     */
    private Node<E> tail;
    /**
     * Поле количества элементов в коллекции
     */
    private int size = 0;
    /**
     * Поле счетчика изменений в коллекции (добавление элементов)
     */
    private int modCount = 0;

    /**
     * Метод используется для добавления узла в коллекцию
     * 1) Если коллекция пустая, присваиваем {@link SimpleLinkedList#head}
     * новый узел с элементом, c ссылкой на предыдущий узел - null и с ссылкой на следующий узел - null
     * 2) Если в коллекции только один элемент
     * 2.1) Присваиваем {@link SimpleLinkedList#tail} новый узел с элементом,
     * с ссылкой на предыдущий узел {@link SimpleLinkedList#head} и ссылкой на следующий узел null
     * 2.2) Меняем уже существующему первому узлу ссылку на следующий узел {@link SimpleLinkedList#tail}
     * 3) Если в коллекции больше двух элементов, присваиваем {@link SimpleLinkedList#tail} новый узел
     * с элементом, так что ссылка на предыдущий элемент будет {@link SimpleLinkedList#tail} до добавления
     * нового элемента в коллекцию и ссылка на следующий узел будет null
     *
     * @param value - элемент
     */
    @Override
    public void add(E value) {
        if (size == 0) {
            head = new Node<>(null, value, null);
            size++;
            modCount++;
        } else if (size == 1) {
            tail = new Node<>(head, value, null);
            head = new Node<>(null, head.item, tail);
            size++;
            modCount++;
        } else if (size > 2) {
            tail = new Node<>(tail.prev.next, value, null);
            size++;
            modCount++;
        }
    }

    /**
     * Метод используется для получения элемента коллекции по индексу
     * 1) Проверяется не выходит ли index заменяемого элемента за пределы размеров коллекции
     * с помощью метода {@link Objects#checkIndex(int, int)}
     * 2) Создаем локальную переменную типа {@link Node}
     * 3) Если искомый индекс равен нулю, то искомый элемент находится в первом узле
     * 4) Если искомый индекс равен последнему элементу коллекции, то искомый элемент находится в последнем узле
     * 5) Иначе запускается цикл, с каждой итерацией переходим по ссылкам на узлы, начиная с первого
     * и как переменная i станет равна индексу, то это значит, что мы дошли до искомого элемента
     *
     * @param index - индекс искомого элемента
     * @return - возвращает искомый элемент
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x;
        if (index == 0) {
            x = head;
        } else if (index == size - 1) {
            x = tail;
        } else {
            x = head;
            for (int i = 1; i != index; i++) {
                x = x.next;
            }
        }
        return x.item;
    }

    /**
     * Анонимный класс {@link Iterator} для корректного перебора элементов коллекции
     *
     * @return - возвращает итератор
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            /**
             * Поле для указателя на котором сейчас находится итератор
             */
            private int iteratorIndex = 0;
            /**
             * Поле изменений для недопущения изменений в коллекции во время работы итератора
             */
            private final int iteratorModCount = modCount;
            /**
             * Поле для указателя узла, на котором сейчас находится итератор
             */
            private Node<E> iteratorNode;

            /**
             * Метод позволяет узнать, есть ли следующий элемент в коллекции
             * 1) Проверяем изменилась ли наша коллекция за время работы итератора
             * если изменилась, то выбрасывается исключение {@link ConcurrentModificationException}
             * @return - true - если следующий элемент есть, false - если нет
             */
            @Override
            public boolean hasNext() {
                if (iteratorModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorIndex < size;
            }

            /**
             * Метод используется для передвижения указателя итератора по коллекции
             * и возврата соответствующего значения
             * 1) Проверяем методом {@link Iterator#hasNext()} есть ли следующий элемент в коллекции
             * если нет, то выбрасывается {@link NoSuchElementException}
             * 2) Если iteratorIndex == 0, то указатель iteratorNode равен
             * первому элементу коллекции {@link SimpleLinkedList#head}
             * 2.1) Выполняем инкремент iteratorIndex
             * 3) Если iteratorIndex != 0, то указатель iteratorNode равен
             * следующему своему узлу
             * 3.1) Выполняем инкремент iteratorIndex
             *
             * @return - возвращаем следующий элемент коллекции
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                iteratorNode = iteratorIndex == 0 ? head : iteratorNode.next;
                iteratorIndex++;
                return iteratorNode.item;
            }
        };
    }
}

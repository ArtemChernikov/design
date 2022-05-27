package ru.job4j.collection;

import java.util.*;

/**
 * Класс описывает модель коллекции ArrayList со свойствами <b>container</b>, <b>size</b> и <b>modCount</b>
 * Класс реализовывает интерфейс {@link SimpleList}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SimpleArrayList<T> implements SimpleList<T> {
    /**
     * Поле хранилища элементов в виде массива
     */
    private T[] container;
    /**
     * Поле количества элементов в коллекции
     */
    private int size;
    /**
     * Поле счетчика изменений в коллекции (добавление или удаление элементов)
     */
    private int modCount;

    /**
     * Конструктор - создание нового объекта с определенным значением
     * {@link SimpleArrayList#container} присваивается массив
     * c определенной вместимостью
     *
     * @param capacity - вместимость массива
     */
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод используется для добавления элемента в коллекцию
     * 1) Если в массиве нет места, для добавления нового элемента,
     * то выполняется метод {@link SimpleArrayList#increase()},
     * 2) происходит добавление элемента и инкремент поля {@link SimpleArrayList#modCount}
     *
     * @param value - добавляемый элемент
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            increase();
        }
        container[size++] = value;
        modCount++;
    }

    /**
     * Метод используется для замены элемента в коллекции
     * 1) Проверяется не выходит ли index заменяемого элемента за пределы массива
     * 2) Присваиваем старой ячейке новый элемент
     *
     * @param index    - индекс заменяемого элемента
     * @param newValue - элемент на который происходит замена
     * @return - возвращает элемент, который заменили
     */
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T rsl = container[index];
        container[index] = newValue;
        return rsl;
    }

    /**
     * Метод используется для удаление элемента из коллекции
     * 1) Проверяется не выходит ли index заменяемого элемента за пределы массива {@link Objects#checkIndex(int, int)}
     * 2) Копируем элементы коллекции, которые идут после удаляемого элемента,
     * на место удаляемого элемента, с помощью метода {@link System#arraycopy(Object, int, Object, int, int)}
     * 3) Присваиваем null последнему элементу нашей коллекции
     * 4) Декремент полей {@link SimpleArrayList#size} и {@link SimpleArrayList#modCount}
     *
     * @param index - индекс удаляемого элемента
     * @return - возвращает удаленный элемент
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T rsl = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return rsl;
    }

    /**
     * Метод используется для получения элемента коллекции по индексу
     * 1) Проверяется не выходит ли index заменяемого элемента за пределы массива {@link Objects#checkIndex(int, int)}
     * 2) Возвращаем искомый элемент
     *
     * @param index - индекс искомого элемента
     * @return - возвращает искомый элемент
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    /**
     * Метод используется для определения количества элементов в коллекции
     *
     * @return - возвращает количество элементов в коллекции
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод используется для расширения коллекции в два раза
     * 1) Для увеличения размера используем метод {@link Arrays#copyOf(int[], int)}
     */
    public void increase() {
        container = Arrays.copyOf(container, container.length * 2);
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
             * Поле для указателя на котором сейчас находится итератор
             */
            private int iteratorIndex = 0;
            /**
             * Поле изменений для недопущения изменений в коллекции во время работы итератора
             */
            private final int expectedModCount = modCount;

            /**
             * Метод позволяет узнать, есть ли следующий элемент в коллекции
             *
             * @return - true - если следующий элемент есть, false - если нет
             */
            @Override
            public boolean hasNext() {
                return iteratorIndex < size;
            }

            /**
             * Метод используется для передвижения указателя итератора по коллекции
             * и возврата соответствующего значения
             * 1) Проверяем методом {@link Iterator#hasNext()} есть ли следующий элемент в коллекции
             * если нет, то выбрасывается {@link NoSuchElementException}
             * 2) Проверяем изменилась ли наша коллекция за время работы итератора
             * если изменилась, то выбрасывается исключение {@link ConcurrentModificationException}
             * 3) Возвращаем следующий элемент коллекции
             *
             * @return - возвращаем следующий элемент коллекции
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[iteratorIndex++];
            }
        };
    }
}

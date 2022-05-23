package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

/**
 * Класс модели шаблона итератора со свойствами <b>data</b> и <b>cursor</b>
 * Класс описывает итерации по итератору со вложенными в него итераторами, по возрастанию
 * по образу и подобию метода flatMap() из Stream API
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class FlatMap<T> implements Iterator<T> {
    /**
     * Поле итератора с вложенными в него итераторами
     */
    private final Iterator<Iterator<T>> data;
    /**
     * Поле курсора на котором сейчас находится наш итератор
     */
    private Iterator<T> cursor = Collections.emptyIterator();

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param data - итератор
     */
    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * Метод позволяет узнать, есть ли следующий элемент в итераторе или нет
     * 1. Проверяем есть ли в основном итераторе элементы
     * 1) если есть присваиваем нашему курсору следующий элемент в основном итераторе
     * 2) запускаем цикл, пока в курсоре нет последующих элементов и у основного итератора есть следующий элемент
     * присваиваем нашему курсору следующий элемент основного итератора
     * 3) возвращаем значение boolean (есть ли в нашем курсоре следующий элемент)
     * <p>
     * 2. 1) если следующего элемента в основном итераторе нет, то присваивания нашему курсору не произойдет
     * 2) возвращаем cursor.hasNext() -> false
     *
     * @return - boolean true - если элемент имеется, false - если наоборот
     */
    @Override
    public boolean hasNext() {
        if (data.hasNext()) {
            cursor = data.next();
            while (!cursor.hasNext() && data.hasNext()) {
                cursor = data.next();
            }
        }
        return cursor.hasNext();
    }

    /**
     * Метод описывает возвращение следующего элемента итератора итераторов,
     * если он отсутствует и вызывается данный метод,
     * то выбрасывается исключение {@link NoSuchElementException}
     *
     * @return - возвращает следующий элемент
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}

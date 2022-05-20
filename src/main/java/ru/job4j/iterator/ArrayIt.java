package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс модели шаблона итератора со свойствами <b>data</b> и <b>point</b>
 * Класс описывает итерацию массива по возрастанию
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ArrayIt implements Iterator<Integer> {
    /**
     * Поле с массивом для проведения итераций
     */
    private final int[] data;
    /**
     * Поле точки на котором сейчас находится итератор
     */
    private int point = 0;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param data - массив
     */
    public ArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Метод позволяет узнать, есть ли следующий элемент в массиве или нет
     *
     * @return - boolean true - если элемент имеется, false - если наоборот
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Метод описывает возвращение следующего элемента массива,
     * если он отсутствует и вызывается данный метод,
     * то выбрасывается исключение {@link NoSuchElementException}
     *
     * @return - возвращает следующий элемент
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}

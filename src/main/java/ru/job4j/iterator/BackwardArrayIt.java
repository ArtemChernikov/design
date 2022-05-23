package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс модели шаблона итератора со свойствами <b>data</b> и <b>point</b>
 * Класс описывает итерации по массиву, по убыванию
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class BackwardArrayIt implements Iterator<Integer> {
    /**
     * Поле с массивом для проведения итераций
     */
    private final int[] data;
    /**
     * Поле точки на котором сейчас находится итератор
     */
    private int point;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param data - массив
     */
    public BackwardArrayIt(int[] data) {
        this.data = data;
        this.point = data.length - 1;
    }

    /**
     * Метод позволяет узнать, есть ли следующий элемент в массиве или нет
     *
     * @return - boolean true - если элемент имеется, false - если наоборот
     */
    @Override
    public boolean hasNext() {
        return point > -1;
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
        return data[point--];
    }
}

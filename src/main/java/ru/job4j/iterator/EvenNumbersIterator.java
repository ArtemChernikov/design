package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс модели шаблона итератора со свойствами <b>data</b> и <b>index</b>
 * Класс описывает итерации по массиву, только по четным числам
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * Поле с массивом для проведения итераций
     */
    private int[] data;
    /**
     * Поле точки на котором сейчас находится итератор
     */
    private int index = 0;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param data - массив
     */
    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Метод позволяет узнать, есть ли следующий четный элемент в массиве или нет
     *
     * @return - boolean true - если элемент имеется, false - если наоборот
     */
    @Override
    public boolean hasNext() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                index = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Метод описывает возвращение следующего четного элемента массива,
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
        return data[index++];
    }
}

package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс модели шаблона итератора со свойствами <b>data</b>, <b>row</b> и <b>column</b>
 * Класс описывает итерацию по двумерному массиву по возрастанию
 * В двумерном массиве допускаются пустые элементы
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class MatrixIt implements Iterator<Integer> {
    /**
     * Поле двумерного массива
     */
    private final int[][] data;
    /**
     * Поле ряда массива
     */
    private int row = 0;
    /**
     * Поле колонны массива
     */
    private int column = 0;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param data - двумерный массив
     */
    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод позволяет узнать, есть ли следующий элемент в массиве или нет
     *
     * @return - boolean true - если элемент имеется, false - если наоборот
     */
    @Override
    public boolean hasNext() {
        if (data.length != 1) {
            while (column != data.length - 1) {
                try {
                    int x = data[column][row];
                    return row != data[data.length - 1].length;
                } catch (ArrayIndexOutOfBoundsException e) {
                    column++;
                    row = 0;
                }
            }
            return row != data[data.length - 1].length;
        }
        try {
            int x = data[column][row];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            if (data.length == 1) {
                return false;
            }
        }
        return true;
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
        return data[column][row++];
    }
}

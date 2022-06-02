package ru.job4j.collection;

import java.util.Iterator;

/**
 * Класс описывает модель коллекции Set со свойством <b>set</b>
 * Класс реализовывает интерфейс {@link Set}
 * В классе используется объект {@link SimpleArrayList} для хранения уникальных элементов
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SimpleSet<T> implements Set<T> {
    /**
     * Поле хранилище уникальных элементов
     */
    private final SimpleArrayList<T> set = new SimpleArrayList<>(10);

    /**
     * Метод ипользуется для добавления уникального элемента в хранилище
     *
     * @param value - элемент
     * @return - возвращает boolean true - при успешном добавлении, false - если иначе
     */
    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод используется для проверки существует ли элемент во множестве или нет
     *
     * @param value - элемент
     * @return - возвращает boolean true - если существует, false - если иначе
     */
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        if (value != null) {
            for (T e : set) {
                if (e != null && e.equals(value)) {
                    rsl = true;
                    break;
                }
            }
        } else {
            for (T e : set) {
                if (e == null) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    /**
     * Используется итератор из {@link SimpleArrayList}
     *
     * @return - возвращает итератор
     */
    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

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
        boolean rsl = !contains(value);
        if (rsl) {
            set.add(value);
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
        for (T e : set) {
            if (Objects.equals(e, value)) {
                rsl = true;
                break;
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

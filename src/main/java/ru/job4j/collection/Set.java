package ru.job4j.collection;

/**
 * Интерфейс, имплементировав который получим
 * простую реализацию коллекции с дефолтными методами
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public interface Set<T> extends Iterable<T> {
    /**
     * Метод используется для добавления элемента во множество
     *
     * @param value - элемент
     * @return - возвращает boolean true - при успешном добавлении, false - если иначе
     */
    boolean add(T value);

    /**
     * Метод используется для проверки существует ли элемент во множестве или нет
     *
     * @param value - элемент
     * @return - возвращает boolean true - если существует, false - если иначе
     */
    boolean contains(T value);
}

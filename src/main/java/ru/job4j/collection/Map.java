package ru.job4j.collection;

/**
 * Интерфейс, имплементировав который получим
 * простую реализацию коллекции с дефолтными методами
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public interface Map<K, V> extends Iterable<K> {
    /**
     * Кладет в коллекцию пару ключ - значение
     *
     * @param key   - ключ
     * @param value - значение
     * @return - возвращает boolean
     */
    boolean put(K key, V value);

    /**
     * Достает из коллекции значение по ключу
     *
     * @param key - ключ
     * @return - возращает значение
     */
    V get(K key);

    /**
     * Изымает элемент из коллекции по ключу
     *
     * @param key - ключ
     * @return - возвращает boolean
     */
    boolean remove(K key);
}

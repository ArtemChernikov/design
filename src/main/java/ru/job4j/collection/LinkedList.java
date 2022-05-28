package ru.job4j.collection;

/**
 * Интерфейс, имплементировав который получим
 * простую реализацию коллекции с дефолтными методами
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public interface LinkedList<E> extends Iterable<E> {
    /**
     * Метод выполняет добавление элемента в коллекцию
     *
     * @param value - элемент
     */
    void add(E value);

    /**
     * Метод для получения элемента коллекции по индексу
     *
     * @param index - индекс искомого элемента
     * @return - возвращает этот элемент
     */
    E get(int index);
}

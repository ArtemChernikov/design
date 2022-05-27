package ru.job4j.generics;

/**
 * Интерфейс описывает абстрактное хранилище с определенными методами
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public interface Store<T extends Base> {
    /**
     * Добавление элемента
     *
     * @param model - элемент
     */
    void add(T model);

    /**
     * Замена элемента
     *
     * @param id    - id элемента
     * @param model - сам элемент
     * @return - возвращает boolean
     */
    boolean replace(String id, T model);

    /**
     * Удаление элемента
     *
     * @param id - id элемента
     * @return - возвращает boolean
     */
    boolean delete(String id);

    /**
     * Поиск по id элемента
     *
     * @param id - id элемента
     * @return - возвращает элемент
     */
    T findById(String id);
}

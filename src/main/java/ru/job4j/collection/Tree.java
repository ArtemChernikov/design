package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, имплементировав который получим
 * простую реализацию структуры дерева с дефолтными методами
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public interface Tree<E> {
    /**
     * Метод для добавления элемента
     *
     * @param parent - родитель
     * @param child  - потомок
     * @return - возвращает boolean
     */
    boolean add(E parent, E child);

    /**
     * Метод для проверки бинарное дерево или нет
     *
     * @return - возвращает boolean
     */
    boolean isBinary();

    /**
     * Метод для поиска элемента
     *
     * @param value - элемент
     * @return - возвращает {@link Optional}
     */
    Optional<Node<E>> findBy(E value);

    /**
     * Вложенный класс описывает модель узла со свойствами <b>value</b> и <b>children</b>
     *
     * @param <E> - тип элемента
     */
    class Node<E> {
        /**
         * Поле значение
         */
        final E value;
        /**
         * Поле список потомков
         */
        final List<Node<E>> children = new ArrayList<>();

        /**
         * Конструктор - создание нового объекта с определенным значением
         *
         * @param value - значение
         */
        public Node(E value) {
            this.value = value;
        }
    }
}

package ru.job4j.collection;

import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Класс описывает упрощенную модель коллекции Tree со свойством <b>root</b>
 * Класс реализовывает интерфейс {@link Tree}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SimpleTree<E> implements Tree<E> {
    /**
     * Поле родитель всех элементов дерева
     */
    private final Node<E> root;

    /**
     * Конструктор - создание нового объекта с определенным значением
     *
     * @param root - родитель
     */
    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод используется для добавления узла в коллекцию по значению родителя
     * Если родителя не существует, то элемент добавлен не будет
     * Если добавляемый элемент уже есть в коллекции, то он добавлен не будет
     * Для поиска родителя используется метод {@link SimpleTree#findBy(Object)}
     *
     * @param parent - родитель добавляемого элемента
     * @param child  - добавляемый элемент (потомок)
     * @return - возвращает true - если элемент успешно добавлен и false - если иначе
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> par = findBy(parent);
        Optional<Node<E>> chi = findBy(child);
        if (par.isPresent() && chi.isEmpty()) {
            par.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод используется для поиска узла в коллекции по значению
     * Метод использует алгоритм для обхода в ширину
     * Если узел по значению не найден то будет возвращен {@link Optional#empty()}
     *
     * @param value - значение узла
     * @return - возвращает узел по искомому значению в виде {@link Optional}
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}

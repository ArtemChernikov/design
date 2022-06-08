package ru.job4j.collection;

import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * Класс описывает упрощенную модель коллекции Tree со свойством <b>root</b>
 * Класс реализовывает интерфейс {@link Tree}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
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
     * Метод используется для проверки бинарное дерево или нет
     *
     * @return - возвращает true - если у каждого узла дерева не больше двух наследников и false - если больше
     */
    @Override
    public boolean isBinary() {
        return findByPredicate(x -> x.children.size() > 2).isEmpty();
    }

    /**
     * Метод используется для итераций по всем элементам дерева
     * и проверки условия заданным {@link Predicate}
     *
     * @param condition - условие
     * @return - возвращает {@link Optional}
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
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
        return findByPredicate(x -> x.value.equals(value));
    }
}

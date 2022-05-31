package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * Класс описывает методы работы {@link ListIterator} реализующие fail-safe поведение
 * и дающие возможность изменения коллекции, во время итераций по ней
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ListUtils {
    /**
     * Метод используется для добавления элемента до определенного индекса
     *
     * @param list  - список
     * @param index - индекс
     * @param value - элемент
     * @param <T>   - тип элементов в коллекции
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод используется для добавления элемента после определенного индекса
     *
     * @param list  - список
     * @param index - индекс
     * @param value - элемент
     * @param <T>   - тип элементов в коллекции
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        if (list.size() - 1 == index) {
            list.add(value);
            return;
        }
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.previousIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод используется для изъятия из коллекции элементов удовлетворяющих {@link Predicate}
     *
     * @param list   - список
     * @param filter - фильтр {@link Predicate}
     * @param <T>    - тип элементов в коллекции
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Метод используется для замены всех элементов на определенное значение, удовлетворяющих {@link Predicate}
     *
     * @param list   - список
     * @param filter - фильтр {@link Predicate}
     * @param value  - элемент на который будет замена
     * @param <T>    - тип элементов коллекции
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
                iterator.add(value);
            }
        }
    }

    /**
     * Метод используется для удаления всех элементов из первого списка,
     * повторяющихся во втором списке
     *
     * @param list     - список где будет произведено удаление дубликатов
     * @param elements - список элементов
     * @param <T>      - тип элементов коллекции
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        for (T element : elements) {
            ListIterator<T> iterator1 = list.listIterator();
            while (iterator1.hasNext()) {
                if (element.equals(iterator1.next())) {
                    iterator1.remove();
                }
            }
        }
    }
}

package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Класс описывает модель хранилища данных сотрудников компании
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class MemStore implements Store {
    /**
     * Поле список сотрудников
     */
    private final List<Employee> employees = new ArrayList<>();

    /**
     * Метод используется для добавления нового сотрудника
     *
     * @param em - сотрудник
     */
    @Override
    public void add(Employee em) {
        employees.add(em);
    }

    /**
     * Метод используется для поиска сотрудников компании по фильтру {@link Predicate}
     *
     * @param filter - фильтр
     * @return - возвращает список "отфильтрованных" сотрудников
     */
    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}

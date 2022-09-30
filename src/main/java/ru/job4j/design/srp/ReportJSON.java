package ru.job4j.design.srp;

import com.google.gson.Gson;

import java.util.function.Predicate;

/**
 * Класс используется для создания отчетов в JSON формате.
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ReportJSON implements Report {

    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson g = new Gson();
        return g.toJson(store.findBy(filter));
    }
}

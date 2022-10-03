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
    private final Gson gson;
    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
        gson = new Gson();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}

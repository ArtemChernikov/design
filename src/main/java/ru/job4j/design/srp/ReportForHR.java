package ru.job4j.design.srp;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс описывает модель создания отчетов для HR отдела
 * (без дат найма и увольнения, но с зарплатой по убыванию)
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ReportForHR implements Report {
    private final Store store;

    public ReportForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> list = store.findBy(filter);
        list.sort(Collections.reverseOrder());
        for (Employee employee : list) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

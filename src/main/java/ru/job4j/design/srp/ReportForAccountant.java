package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

/**
 * Класс описывает модель создания отчетов для отдела бухгалтерии
 * (с измененным видом зарплаты - рубли переведены в доллары)
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ReportForAccountant implements Report {
    public static final int DOLLAR_RUBLE = 60;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ReportForAccountant(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary() / DOLLAR_RUBLE).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

package ru.job4j.design.srp;

import java.util.Calendar;
import java.util.Objects;

/**
 * Класс описывает модель сотрудника компании со свойствами <b>name</b>, <b>hired</b>,
 * <b>fired</b> и <b>salary</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Employee implements Comparable<Employee> {
    /**
     * Поле имя сотрудника
     */
    private String name;
    /**
     * Поле дата найма сотрудника
     */
    private Calendar hired;
    /**
     * Поле дата увольнения сотрудника
     */
    private Calendar fired;
    /**
     * Поле зарплата сотрудника
     */
    private double salary;

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Employee o) {
        return (int) (this.salary - o.salary);
    }
}

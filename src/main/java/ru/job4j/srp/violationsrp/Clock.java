package ru.job4j.srp.violationsrp;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Класс описывает намеренное нарушение первого принципа SOLID
 * SRP (Single Responsibility Principle), для демонстрации
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Clock {
    private LocalTime time;
    private LocalDate date;

    private int charge;

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void resetTime() {
        time = LocalTime.now();
    }

    public void resetDate() {
        date = LocalDate.now();
    }

    public void charge() {
        charge = 100;
    }
}

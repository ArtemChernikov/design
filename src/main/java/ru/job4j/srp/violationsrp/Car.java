package ru.job4j.srp.violationsrp;

/**
 * Класс описывает намеренное нарушение первого принципа SOLID
 * SRP (Single Responsibility Principle), для демонстрации
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Car {
    private String engine;
    private String model;
    private String body;
    private String color;

    public Car(String engine, String model, String body, String color) {
        this.engine = engine;
        this.model = model;
        this.body = body;
        this.color = color;
    }

    public void washCar() {
        System.out.println("Машина чиста до скрипа.");
    }

    public void fixCar() {
        System.out.println("Ваша ласточка вновь на ходу!");
    }

    public void refuel() {
        System.out.println("Ваша машина заправлена!");
    }
}

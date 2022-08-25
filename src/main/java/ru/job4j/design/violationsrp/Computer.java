package ru.job4j.design.violationsrp;

import java.io.File;

/**
 * Класс описывает намеренное нарушение первого принципа SOLID
 * SRP (Single Responsibility Principle), для демонстрации
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Computer {
    private String model;
    private int ssd;
    private int memory;


    public Computer(String model, int ssd, int memory) {
        this.model = model;
        this.ssd = ssd;
        this.memory = memory;
    }

    public void upMemory(int size) {
        memory += size;
    }

    public void upSsd(int size) {
        ssd += size;
    }

    public void saveFile(File file) {
        System.out.println("Файл успешно сохранен");
    }
}

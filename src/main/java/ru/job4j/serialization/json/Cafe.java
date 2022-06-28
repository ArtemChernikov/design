package ru.job4j.serialization.json;


import java.util.Arrays;

/**
 * Класс описывает модель кафе со свойствами <b>name</b>, <b>address</b>, <b>open</b>, <b>tables</b> и <b>menu</b>
 * Используется класс {@link Address}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Cafe {
    /**
     * Поле название
     */
    private final String name;
    /**
     * Поле адрес
     */
    private final Address address;
    /**
     * Поле кафе открыто
     */
    private final boolean open;
    /**
     * Поле количество столиков
     */
    private final int tables;
    /**
     * Поле меню
     */
    private final String[] menu;

    /**
     * Конструктор - создание объекта с определенными значениями
     *
     * @param name    - название
     * @param address - адрес
     * @param open    - открыто ли кафе
     * @param tables  - количество столиков
     * @param menu    - меню
     */
    public Cafe(String name, Address address, boolean open, int tables, String[] menu) {
        this.name = name;
        this.address = address;
        this.open = open;
        this.tables = tables;
        this.menu = menu;
    }

    /**
     * Метод переопределяется для наилучшего вывода объекта и его свойств в консоль
     *
     * @return - возвращает объект в виде строки
     */
    @Override
    public String toString() {
        return "Cafe{"
                + "name='" + name + '\''
                + ", address=" + address
                + ", open=" + open
                + ", tables=" + tables
                + ", menu=" + Arrays.toString(menu)
                + '}';
    }
}

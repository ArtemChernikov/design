package ru.job4j.serialization.json;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс описывает модель кафе со свойствами <b>name</b>, <b>address</b>, <b>open</b>, <b>tables</b> и <b>menu</b>
 * Используется класс {@link Address}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
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
     * Метод используется для получения поля {@link Cafe#name}
     *
     * @return - возвращает имя
     */
    public String getName() {
        return name;
    }

    /**
     * Метод используется для получения поля {@link Cafe#address}
     *
     * @return - возвращает адрес
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Метод используется для получения поля {@link Cafe#open}
     *
     * @return - возвращает boolean открыто кафе или нет
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Метод используется для получения поля {@link Cafe#tables}
     *
     * @return - возвращает количество столиков
     */
    public int getTables() {
        return tables;
    }

    /**
     * Метод используется для получения поля {@link Cafe#menu}
     *
     * @return - возвращает меню
     */
    public String[] getMenu() {
        return menu;
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

    /**
     * Метод используется для демонстарции сериализации объекта
     * {@link Cafe} в формат JSON с помощью {@link JSONObject}
     *
     * @param args - входные параметры запуска
     */
    public static void main(String[] args) {
        /* JSONObject из json-строки */
        JSONObject jsonAddress = new JSONObject(
                "{"
                        + "\"city\":\"Moscow\","
                        + "\"street\":\"Ubileynaya\","
                        + "\"house\":30,"
                        + "\"apartment\":4"
                        + "}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Borsh");
        list.add("ChikenSoup");
        list.add("CheeseCake");
        JSONArray jsonMenu = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Cafe cafe = new Cafe("LuxuryCafe", new Address("Moscow", "Ubileynaya", 30, 4),
                true, 30, new String[]{"Borsh", "ChikenSoup", "CheeseCake"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", cafe.getName());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("open", cafe.isOpen());
        jsonObject.put("tables", cafe.getTables());
        jsonObject.put("menu", jsonMenu);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(cafe));
    }
}

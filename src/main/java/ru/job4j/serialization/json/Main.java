package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Класс демонстрирует создание объекта {@link Cafe}, сериализацию
 * в формат JSON и обратно в POJO с помощью {@link Gson}
 * Используется класс {@link Cafe}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        final Cafe cafe = new Cafe("LuxuryCafe", new Address("Moscow", "Ubileynaya", 30, 4),
                true, 30, new String[]{"Borsh", "ChikenSoup", "CheeseCake"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cafe));
        final String cafeJson =
                "{"
                        + "\"name\":\"LuxuryCafe\","
                        + "\"Address\":"
                        + "{"
                        + "\"city\":\"Moscow\","
                        + "\"street\":\"Ubileynaya\","
                        + "\"house\":30,"
                        + "\"apartment\":4"
                        + "},"
                        + "\"open\":true,"
                        + "\"tables\":30,"
                        + "\"menu\":"
                        + "[\"Borsh\",\"ChikenSoup\", \"CheeseCake\"]"
                        + "}";
        final Cafe cafeMod = gson.fromJson(cafeJson, Cafe.class);
        System.out.println(cafeMod);
    }
}

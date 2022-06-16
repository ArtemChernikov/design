package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс описывает модель конфигурации со свойствами <b>path</b> и <b>values</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Config {
    /**
     * Поле путь к файлу с конфигурацией
     */
    private final String path;
    /**
     * Поле хранилище пар ключ - значение из файла конфигурации
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param path - путь к файлу
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод используется для извлечения из файла пар ключ-значение
     * и дальнейшей их вставки в {@link Config#values}
     * Метод пропускает пустые строки и строки с комментариями
     * 1) Фильтруем строки с комментариями
     * 2) Фильтруем пустые строки
     * 3) Проходимся по каждой строке и если она не содержит символа '='
     * то выбрасывается исключение {@link IllegalArgumentException}
     * 4) Обрезаем строку по символу '='
     * 5) Проверяем не пустой ли ключ или значение, если одно из низ пустое
     * то выбрасывается исключение {@link IllegalArgumentException}
     * 6) Вставляем в {@link Config#values} нашу пару
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .filter(x -> !x.startsWith("#"))
                    .filter(x -> x.length() > 0)
                    .forEach(i -> {
                        if (!i.contains("=")) {
                            throw new IllegalArgumentException();
                        }
                        String[] ar = i.split("=", 2);
                        if (ar[0].equals("") || ar[1].equals("")) {
                            throw new IllegalArgumentException();
                        }
                        values.put(ar[0], ar[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод используется для получения значения по ключу в {@link Config#values}
     *
     * @param key - ключ
     * @return - возвращает значение
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}

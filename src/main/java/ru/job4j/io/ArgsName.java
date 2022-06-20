package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс описывает модель программы, которая разбивает входные параметры запуска
 * на пары ключ-значение со свойством <b>values</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class ArgsName {
    /**
     * Поле хранилище для хранения пар ключ-значение
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Метод используется для получения значения по ключу из {@link ArgsName#values}
     * Если заданный ключ отсутствует, то выбрасывается исключение {@link IllegalArgumentException}
     *
     * @param key - ключ для поиска значения
     * @return - возвращает значение ключу
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Такого ключа не существует");
        }
        return values.get(key);
    }

    /**
     * Метод используется для разделения каждой строки входных
     * параметров запуска на пары ключ-значение и вставки их в {@link ArgsName#values}
     *
     * @param args - входные параметры запуска
     */
    private void parse(String[] args) {
        for (String e : args) {
            String[] array = inputValidate(e);
            values.put(array[0].substring(1), array[1]);
        }
    }

    /**
     * Метод используется для валидации входных параметров запуска
     *
     * @param str - входные параметры запуска
     */
    private String[] inputValidate(String str) {
        if (!str.startsWith("-") || !str.contains("=") || str.startsWith("-=")) {
            throw new IllegalArgumentException("Задан неккоректный параметр запуска");
        }
        String[] array = str.split("=", 2);
        if (array[1].isEmpty()) {
            throw new IllegalArgumentException("Отсутствует значение в параметре запуска");
        }
        return array;
    }

    /**
     * Метод используется для создания новго объекта {@link ArgsName}
     * на основе входных параметров запуска
     *
     * @param args - параметры запуска
     * @return - возвращает объект {@link ArgsName}
     */
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Входные параметры запуска отсутствуют");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

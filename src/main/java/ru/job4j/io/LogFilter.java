package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс описывает пример чтения из файла с помощью {@link BufferedReader}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class LogFilter {
    /**
     * Метод используется для чтения строк из файла и добавления нужных нам строк
     * в результирующий список
     *
     * @param file - файл из которого читаем
     * @return - возвращат список
     */
    public List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            result = br.lines().filter(x -> x.contains(" 404 ")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}

package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс описывает пример чтения из файла с помощью {@link BufferedReader}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
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
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            result = br.lines().filter(x -> x.contains(" 404 ")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод используется для записи строк в файл
     *
     * @param log  - список строк
     * @param file - файл для записи
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String s : log) {
                writer.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
    }
}

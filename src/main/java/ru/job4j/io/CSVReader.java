package ru.job4j.io;

import java.io.*;
import java.util.*;

/**
 * Класс демонстрирует чтение из csv файла и вывод данных в файл или в консоль
 * в зависимости от входных параметров запуска
 * Класс должен запуска с входными параметрами запуска например:
 * "-path=file.csv -delimiter=";"  -out=stdout -filter=name,age"
 * где path - пусть к файлу с данными, delimiter - разделитель каждого столбца с данными,
 * out - путь вывода (если "stdout" - выводим данные в консоль, если же путь к файлу, то записываем в него данные)
 * filter - фильтр столбцов (какие столбцы нам нужны для записи в файл)
 * Используется класс {@link SearchFiles} - для поиска и фильтрации папок и директорий
 * и {@link ArgsName} - для валидции входных параметров и получения значения по ключу
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class CSVReader {
    /**
     * Метод используется для считывания данных и последующей их записи в файл или для вывода в консоль
     * 1) Достаем фильтры из параметра запуска и кладем в массив
     * 2) Узнаем что делать с данными в зависимости от аргумента параметра запуска (пишем в файл или выводим в консоль)
     * 3) Создаем экземпляр {@link Scanner} для чтения и экземпляр {@link PrintStream} для записи или вывода в консоль
     * 4) Делим первую строку с названиями столбцов по разделителю
     * 5) Записываем в файл или выводим на консоль названия столбцов кторые нам нужны (с разделителем между названиями)
     * 6) Запускаем цикл (пока в файле есть элементы), так как первая строка с названиями столбцов уже прочитана и
     * остались только строки с значениями столбцов
     * 7) Записываем в файл или выводим на консоль значения, которые нам нужны (с разделителем между значениями)
     *
     * @param argsName - {@link ArgsName} входные параметры запуска в виде "ключ-значение"
     */
    public static void handle(ArgsName argsName) {
        String[] filter = argsName.get("filter").split(",");
        String out = argsName.get("out");
        try (Scanner readScanner = new Scanner(new FileReader(argsName.get("path")));
             PrintStream writer = new PrintStream("stdout".equals(argsName.get("out")) ? System.out : new FileOutputStream(out))) {
            String[] keys = readScanner.nextLine().split(argsName.get("delimiter"));
            for (int i = 0; i < filter.length; i++) {
                writer.write(filter[i].getBytes());
                if (i != filter.length - 1) {
                    writer.write(argsName.get("delimiter").getBytes());
                } else {
                    writer.println();
                }
            }
            while (readScanner.hasNext()) {
                String[] values = readScanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < filter.length; i++) {
                    writer.write(values[indexOf(filter[i], keys)].getBytes());
                    if (i != filter.length - 1) {
                        writer.write(argsName.get("delimiter").getBytes());
                    } else {
                        writer.println();
                    }
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод используется для определения индекса столбца с нужным нам значением по фильтру
     *
     * @param filter - фильтр для значений
     * @param keys   - массив содеражищий значения
     * @return - возвращает индекс искомого значения по фильтру
     */
    private static int indexOf(String filter, String[] keys) {
        int rsl = -1;
        for (int i = 0; i < keys.length; i++) {
            if (filter.equals(keys[i])) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод используется для валидации входных параметров запуска
     *
     * @param args - входные параметры запуска
     * @return - возвращает {@link ArgsName}
     */
    public static ArgsName inputValidate(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Добавьте четыре входных параметра запуска.");
        }
        ArgsName argsName = ArgsName.of(args);
        File file = new File(argsName.get("path"));
        if (!file.exists() || !file.isFile() || !file.getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Первый параметр запуска должен быть корректен");
        }
        if (argsName.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Укажите корректный разделитель строк");
        }
        if (!"stdout".equals(argsName.get("out")) && !new File(argsName.get("out")).getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Укажите корректный файл для записи");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Укажите корректный фильтр столбцов");
        }
        return argsName;
    }

    public static void main(String[] args) {
        ArgsName argsName = inputValidate(args);
        handle(argsName);
    }
}

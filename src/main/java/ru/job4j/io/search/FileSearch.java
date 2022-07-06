package ru.job4j.io.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import static ru.job4j.io.Search.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс описывает модель программы, для поиска файла по имени, маске или регулярному выражению
 * Используется класс {@link SearchFiles} - для поиска и фильтрации файлов и директорий
 * и {@link ArgsName} - для валидции входных параметров и получения значения по ключу
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class FileSearch {
    /**
     * Метод используется для определения по какому критерию искать файл
     * 1) Если в тип поиска "name" - то сравниваем файлы для дальнейшей фильтрации по имени файла
     * 2) Если в тип поиска "regex" - то сравниваем файлы для дальнейшей фильтрации по регулярному выражению
     * 2) Если в тип поиска "mask" - то переводим маску в регулярное выражение и
     * сравниваем файлы для дальнейшей фильтрации по регулярному выражению
     *
     * @param args - {@link ArgsName} - для удобства изъятия входных парметров запуска
     * @return - возвращает {@link Predicate<Path>} для фильтрации ненужных файлов
     */
    private static Predicate<Path> getSearchCondition(ArgsName args) {
        Predicate<Path> predicate;
        String searchType = args.get("t");
        String file = args.get("n");
        switch (searchType) {
            case "name":
                predicate = p -> file.equals(p.toFile().getName());
                break;
            case "regex":
                predicate = p -> p.toString().matches(file);
                break;
            case "mask":
                String pattern = file.replace(".", "[.]").replace("?", ".?").replace("*", ".*");
                predicate = p -> p.toString().matches(pattern);
                break;
            default:
                predicate = null;
        }
        return predicate;
    }

    /**
     * Метод используется для валидации вхожных параметров запуска
     *
     * @param args - входные параметры запуска
     * @return - возвращает {@link ArgsName}, для удобства изъятия входных парметров запуска
     */
    public ArgsName validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Неверное количество парметров запуска: " + args.length);
        }
        File file = new File(argsName.get("d"));
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Укажите корректный путь к каталогу в котором производить поиск");
        }

        if (!argsName.get("n").matches(".+\\..+")) {
            throw new IllegalArgumentException("Укажите корректное имя файла (целиком, по маске, по регулярному выражению)");
        }
        if (!argsName.get("t").matches(("mask|name|regex"))) {
            throw new IllegalArgumentException("Укажите корректный тип поиска (\"mask\", \"name\", \"regex\")");
        }
        File recordFile = new File(argsName.get("o"));
        if (!recordFile.exists() || !recordFile.isFile()) {
            throw new IllegalArgumentException("Укажите корректный путь к файлу для записи");
        }
        return argsName;
    }

    /**
     * Метод используется для записи абсолютных путей (совпадений имен файлов с искомым файлом) в отдельный файл
     * Для поиска используем метод {@link ru.job4j.io.Search#search(Path, Predicate)} где
     * path - это директория в которой начинаем поиск, а Predicate - способ фильтрации, полученный
     * с помощью метода {@link FileSearch#getSearchCondition(ArgsName)}
     *
     * @param argsName - {@link ArgsName}
     */
    public void writeFiles(ArgsName argsName) {
        Path start = Paths.get(argsName.get("d"));
        try {
            List<Path> paths = search(start, getSearchCondition(argsName));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("o")))) {
                for (Path p : paths) {
                    writer.write(String.valueOf(p.toAbsolutePath()));
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

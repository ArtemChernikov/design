package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс демонстрирует сканирование файловой системы
 * Используется класс {@link SearchFiles}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class Search {
    public static void main(String[] args) throws IOException {
        inputValid(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Метод используется для поиска путей по {@link Predicate}
     *
     * @param root      - корневой путь
     * @param condition - фильтр {@link Predicate}
     * @return - возвращает отфильтрованный список
     * @throws IOException - может выбросить исключение {@link IOException}
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Метод используется для валидации входных параметров запуска,
     * если аргументы запуска отсутствуют, то выбрасывается исключение {@link IllegalArgumentException}
     *
     * @param args - аргументы запуска
     */
    public static void inputValid(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Добавьте два входных параметра запуска.");
        }
        File file = new File(args[0]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Указан неккоректный путь в первом параметре запуска, укажите директорию");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Укажите корректное расширение файла втором параметре запуска");
        }
    }
}

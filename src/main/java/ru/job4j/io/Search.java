package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс демонстрирует сканирование файловой системы
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith(".js")).forEach(System.out::println);
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
}

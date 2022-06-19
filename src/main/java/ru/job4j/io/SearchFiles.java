package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Класс описывает модель для обхода всех файлов и директорий
 * относительно изначального пути со свойствами <b>predicate</b> и <b>paths</b>
 * Наследуется от класса {@link SimpleFileVisitor}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SearchFiles extends SimpleFileVisitor<Path> {
    /**
     * Поле {@link Predicate} для фильтрации
     */
    private final Predicate<Path> predicate;
    /**
     * Поле список с отфильтрованными путями
     */
    private final List<Path> paths = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param predicate - фильтр
     */
    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    /**
     * Метод используется для обхода всех папок и директорий и
     * если путь файла удовлетворяет {@link SearchFiles#predicate},
     * то путь добавляется в список {@link SearchFiles#paths}
     *
     * @param file  - путь
     * @param attrs - атрибуты файла
     * @return -  возвращает {@link FileVisitResult}
     * @throws IOException - может быть выброшено {@link IOException}
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            paths.add(file);
        }
        return CONTINUE;
    }

    /**
     * Метод используется для возврата списка с путями
     *
     * @return - список {@link SearchFiles#paths}
     */
    public List<Path> getPaths() {
        return paths;
    }
}

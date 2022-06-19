package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Точка запуска программы по поиску дубликатов файлов
 * Используется класс {@link DuplicatesVisitor}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        duplicatesVisitor.getDuplicates();
    }
}

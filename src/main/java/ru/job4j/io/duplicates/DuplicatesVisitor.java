package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Класс описывает модель для обхода всех файлов и директорий
 * относительно изначального пути со свойством <b>duplicates</b>
 * Наследуется от класса {@link SimpleFileVisitor}
 * Используется класс {@link FileProperty}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    /**
     * Поле хранилище пар ключ - {@link FileProperty}, значение - список путей к файлам
     */
    private final Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    /**
     * Метод используется для обхода всех файлов и директорий
     * и добавления в {@link DuplicatesVisitor#duplicates} дубликатов файлов
     * 1) Создается локальный {@link File} и проверяется это файл или директория
     * если директория, то продолжаем обход
     * 2) Если файл, то создаем новый объект {@link FileProperty}
     * 3) Проверяем содержится ли ключ только что созданного объекта в {@link DuplicatesVisitor#duplicates}
     * 4) Если такого ключа еще нет, то вставляем его в коллекцию
     * 5) Вставляем в список (значение по ключу) новый путь
     *
     * @param file  - путь к файлу
     * @param attrs - атрибуты файла
     * @return - возвращает {@link FileVisitResult}
     * @throws IOException - может быть выброшено {@link IOException}
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File rsl = new File(file.toUri());
        if (rsl.isFile()) {
            FileProperty fileProperty = new FileProperty(rsl.length(), rsl.getName());
            if (!duplicates.containsKey(fileProperty)) {
                duplicates.put(fileProperty, new ArrayList<>());
            }
            duplicates.get(fileProperty).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    /**
     * Метод используется для вывода в консоль всех дубликатов в формате:
     * "Имя файла - размер файла
     * абсолютный путь к первому дубликату
     * абсолютный путь ко второму дубликату"
     * и.т.д
     */
    public void getDuplicates() {
        duplicates.entrySet().stream()
                .filter(x -> x.getValue().size() > 1)
                .forEach(element -> {
                    System.out.println(element.getKey().getName() + " - " + element.getKey().getSize() + " bytes");
                    element.getValue().forEach(System.out::println);
                });
    }
}

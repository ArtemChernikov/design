package ru.job4j.io;

import java.io.File;

/**
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class Dir {
    /**
     * Метод демонстрирует вывод всех файлов директории и их размер
     *
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.printf("Имя файла - %s; Размер файла - %d%n", subfile.getName(), subfile.length());
        }
    }
}

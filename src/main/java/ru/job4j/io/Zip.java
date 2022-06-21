package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.*;

/**
 * Класс демонстрирует модель архивации файлов с
 * входными параметрами запуска "-d=c:\project\job4j\ -e=.class -o=project.zip" где:
 * d папка для архивации; e - тип файлов которые не будут добавлены в архив; o - название архива
 * Используется класс {@link SearchFiles} - для поиска и фильтрации папок и директорий
 * и {@link ArgsName} - для валидции входных параметров и получения значения по ключу
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Zip {
    /**
     * Метод используется для упаковки в архив файлов и папок из списка в файл назначения
     * 1) Проходимся по списку с путями циклом
     * 2) Каждый путь проверяется абсолютный он или нет
     * 3) Добавляем файл (без содержимого) или директорию в архив
     * 4) Добавляем содержимое файла или директории в архив
     *
     * @param sources - список с путями папок и файлов
     * @param target  - архив
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                String p = path.isAbsolute() ? String.valueOf(path) : String.valueOf(path.toAbsolutePath());
                zip.putNextEntry(new ZipEntry(p));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(p))) {
                    zip.write(out.readAllBytes());
                } catch (IOException c) {
                    c.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод используется для добавления конкретного файла в архив
     *
     * @param source - файл
     * @param target - архивированный файл
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName arg = ArgsName.of(args);
        Zip zip = new Zip();
        if (new File(arg.get("d")).exists()) {
            try {
                zip.packFiles(search(Paths.get(arg.get("d")), p -> !p.endsWith(arg.get("e"))),
                        new File(arg.get("o")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Такой папки не существует");
        }
    }
}

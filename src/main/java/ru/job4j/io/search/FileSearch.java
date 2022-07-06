package ru.job4j.io.search;

import ru.job4j.io.ArgsName;

import static ru.job4j.io.Search.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class FileSearch {
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

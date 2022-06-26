package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String[] filter = argsName.get("filter").split(",");
        String out = argsName.get("out");
        try (Scanner readScanner = new Scanner(new FileReader(argsName.get("path")))) {
            PrintStream writer = new PrintStream("stdout".equals(argsName.get("out")) ? System.out : new FileOutputStream(out));
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
            writer.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

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

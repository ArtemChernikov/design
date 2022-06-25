package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String[] filter = argsName.get("filter").split(",");
        try (Scanner readScanner = new Scanner(new FileReader(argsName.get("path")));
             BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("out")))) {
            String[] keys = readScanner.nextLine().split(argsName.get("delimiter"));
            String path = argsName.get("out");
            if ("stdout".equals(path)) {
                for (int i = 0; i < filter.length; i++) {
                    System.out.print(filter[i]);
                    if (i != filter.length - 1) {
                        System.out.print(argsName.get("delimiter"));
                    } else {
                        System.out.println();
                    }
                }
                while (readScanner.hasNext()) {
                    String[] values = readScanner.nextLine().split(argsName.get("delimiter"));
                    for (int i = 0; i < filter.length; i++) {
                        System.out.print(values[indexOf(filter[i], keys)]);
                        if (i != filter.length - 1) {
                            System.out.print(argsName.get("delimiter"));
                        } else {
                            System.out.println();
                        }
                    }
                }
            } else {
                for (int i = 0; i < filter.length; i++) {
                    writer.write(filter[i]);
                    if (i != filter.length - 1) {
                        writer.write(argsName.get("delimiter"));
                    } else {
                        writer.newLine();
                    }
                }
                while (readScanner.hasNext()) {
                    String[] values = readScanner.nextLine().split(argsName.get("delimiter"));
                    for (int i = 0; i < filter.length; i++) {
                        writer.write(values[indexOf(filter[i], keys)]);
                        if (i != filter.length - 1) {
                            writer.write(argsName.get("delimiter"));
                        } else {
                            writer.newLine();
                        }
                    }
                }
            }
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

    public ArgsName inputValidate(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Добавьте четыре входных параметра запуска.");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!new File(argsName.get("path")).getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Укажите файл с расширением .csv");
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
}

package ru.job4j.io;

import java.io.*;

/**
 * Класс описывает модель анализа доступности сервера
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Analizy {
    /**
     * Метод используется для проведения анализа доступности сервера
     * по входящим данным (имеют формат "статус" "время") из файла,
     * статус может иметь 4 значения (200, 300) - сервер доступен
     * и (400, 500) - сервер недоступен. В файл записываются данные
     * в какое время сервер был недоступен в формате
     * "время отключения";"время подключения";
     *
     * @param source - файл с исходными данными
     * @param target - файл для записи
     */
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean works = true;
            String str;
            while ((str = reader.readLine()) != null) {
                String[] array = str.split(" ");
                if (Integer.parseInt(array[0]) == 400 || Integer.parseInt(array[0]) == 500) {
                    if (works) {
                        writer.write(array[1] + ";");
                    }
                    works = false;
                } else {
                    if (!works) {
                        writer.write(array[1] + ";" + System.lineSeparator());
                        works = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("source.txt", "target.txt");
    }
}

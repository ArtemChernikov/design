package ru.job4j.io;

import java.io.*;

/**
 * Класс описывает чтение чисел из файла при помощи {@link BufferedReader}
 * и вывода результата четное число или нет
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("even.txt")))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(Integer.parseInt(line) % 2 == 0 ? "Четное" : "Нечетное");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

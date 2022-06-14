package ru.job4j.io;

import java.io.*;

/**
 * Класс описывает запись в файл при помощи {@link BufferedWriter}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ResultFile {
    /**
     * Метод используется для демонстрации записи в файл таблицы умножения
     *
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        int[][] array2 = multipleInt(10);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result2.txt"))) {
            for (int[] ints : array2) {
                for (int anInt : ints) {
                    writer.write(anInt);
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод используется для создания двумерного массива с таблицей умножения
     *
     * @param size - размер таблицы умножения
     * @return - возвращает двумерный массив
     */
    public static int[][] multipleInt(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }
        return array;
    }
}

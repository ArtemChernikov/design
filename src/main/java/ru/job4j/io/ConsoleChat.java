package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс описывает модель консольного чата пользователя с ботом
 * со свойствами <b>OUT</b>, <b>STOP</b>, <b>CONTINUE</b>, <b>path</b> и <b>botAnswers</b>
 * Весь диалог записывается в файл
 * Бот отвечает случайными фразами из файла
 * Завершение, пауза или продолжение работы программы выполняется с помощью ключевых слов
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ConsoleChat {
    /**
     * Поле ключевое слово "закончить" - полностью закрывает чат
     */
    private static final String OUT = "закончить";
    /**
     * Поле ключевое слово "стоп" - приостанавливает чат
     */
    private static final String STOP = "стоп";
    /**
     * Поле ключевое слово "продолжить" - продолжает работу в чате
     */
    private static final String CONTINUE = "продолжить";
    /**
     * Поле путь к файлу для записи диалога
     */
    private final String path;
    /**
     * Поле путь к файлу с ответами бота
     */
    private final String botAnswers;

    /**
     * Конструктор - создание объекта с определенными значениями
     *
     * @param path       - путь к диалогу
     * @param botAnswers - путь к ответам бота
     */
    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод используется для запуска программы
     * 1) Создаем локальную переменную "works" с изначальным значением true,
     * которая определяет работает программа или нет
     * 2) Создаем локальную переменную "process" с изначальным значением true,
     * которая определяет приостановлен чат или нет
     * 3) Создаем локальную переменную "log", для сохранения каждой фразы диалога
     * и дальнейшей передачи в метод {@link ConsoleChat#saveLog(List)}
     * 4) Создаем локальную переменную "answers", инициализиурем ее методом
     * {@link ConsoleChat#readPhrases()} - это будет список ответов бота
     * 5) Цикл пока "works = true"
     * - Вводим фразу от пользователя с помощью метода {@link Scanner#nextLine()}
     * и добавляем ее же в список "log"
     * - Проверяем не содержит ли фраза пользователя {@link ConsoleChat#OUT},
     * если содержит закрываем программу "works = false" и "process = false"
     * - Проверяем не содержит ли фраза пользователя строки {@link ConsoleChat#STOP}
     * или {@link ConsoleChat#CONTINUE}, если содержит присваиваем переменной "process"
     * true или false в зависимости от значения
     * - Проверяем приостановлен чат или нет, если нет то выводим в консоль случайный ответ
     * из списка "answers" и добавляем его в список "log"
     * 6) Испоьзуем метод {@link ConsoleChat#saveLog(List)} со списком "log" в параметрах,
     * для сохранения диалога в файл
     */
    public void run() {
        boolean works = true;
        boolean process = true;
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        try (Scanner scanner = new Scanner(System.in)) {
            while (works) {
                String question = scanner.nextLine();
                log.add(question);
                if (question.contains(OUT)) {
                    works = false;
                    process = false;
                }
                if (question.equals(CONTINUE) || question.equals(STOP)) {
                    process = question.equals(CONTINUE);
                }
                if (process) {
                    String answer = answers.get((int) (Math.random() * answers.size()));
                    System.out.println(answer);
                    log.add(answer);
                }
            }
        }
        saveLog(log);
    }

    /**
     * Метод используется для чтения из файла фраз бота
     * и сохранения их в результирующий список
     *
     * @return - список фраз бота
     */
    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        String answer;
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            while ((answer = reader.readLine()) != null) {
                answers.add(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    /**
     * Метод используется для сохранения в файл диалога бота с пользователем
     *
     * @param log - диалог в виде списка
     */
    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String str : log) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("log.txt", "answers.txt");
        cc.run();
    }
}

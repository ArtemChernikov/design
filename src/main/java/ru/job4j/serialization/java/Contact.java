package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

/**
 * Класс описывает модель контакта со свойствами <b>serialVersionUID</b>, <b>zipCode</b> и <b>phone</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Contact implements Serializable {
    /**
     * Поле идентификатор версии объекта
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * Поле почтовый индекс
     */
    private final int zipCode;
    /**
     * Поле номер телефона
     */
    private final String phone;

    /**
     * Конструктор - создание объекта с определенными свойствами
     *
     * @param zipCode - почтовый индекс
     * @param phone   - номер телефона
     */
    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    /**
     * Метод используется для получения поля {@link Contact#zipCode}
     *
     * @return - возвращает почтовый индекс
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Метод используется для получения поля {@link Contact#phone}
     *
     * @return - возвращает номер телефона
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Метод переопределяется для наилучшего вывода объекта и его свойств в консоль
     *
     * @return - возвращает объект в виде строки
     */
    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    /**
     * Метод демонстрирует сериализацию объекта, добавление его в временный файл,
     * десериализацию и дальнейший вывод в консоль объекта из временного файла
     *
     * @param args - входные параметры запуска
     * @throws IOException            - может выбросить исключение
     * @throws ClassNotFoundException - - может выбросить исключение
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}

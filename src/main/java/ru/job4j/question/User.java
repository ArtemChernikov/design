package ru.job4j.question;

import java.util.Objects;

/**
 * Класс модели пользователя со свойствами <b>id</b> и <b>name</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class User {
    /**
     * Поле id пользователя
     */
    private int id;
    /**
     * Поле имя пользователя
     */
    private String name;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param id   - id
     * @param name - имя
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод для получения значения поля {@link User#id}
     *
     * @return - возвращает id пользователя
     */
    public int getId() {
        return id;
    }

    /**
     * Метод для установки значения полю {@link User#id}
     *
     * @param id - id пользователя
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод для получения значения поля {@link User#name}
     *
     * @return - возвращает имя пользователя
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для установки значения полю {@link User#name}
     *
     * @param name - имя пользователя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Переопределенный метод, для корректного сравнения объектов
     *
     * @param o - другой аккаунт
     * @return возвращает true, если объекты равны, false если нет
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    /**
     * Переопределенный метод, для получения числового представления объекта
     *
     * @return - возвращает hashcode объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

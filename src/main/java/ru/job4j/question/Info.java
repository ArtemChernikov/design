package ru.job4j.question;

import java.util.Objects;

/**
 * Класс модели статистики хранилища со свойствами <b>added</b>, <b>changed</b> и <b>deleted</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Info {
    /**
     * Поле добавлено элементов
     */
    private int added;
    /**
     * Поле изменено элементов
     */
    private int changed;
    /**
     * Поле удалено элементов
     */
    private int deleted;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param added   - добавлено
     * @param changed - изменено
     * @param deleted - удалено
     */
    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    /**
     * Метод для получения значения поля {@link Info#added}
     *
     * @return - возвращает количество добавленных элементов
     */
    public int getAdded() {
        return added;
    }

    /**
     * Метод для установки значения полю {@link Info#added}
     *
     * @param added - добавлено элементов
     */
    public void setAdded(int added) {
        this.added = added;
    }

    /**
     * Метод для получения значения поля {@link Info#changed}
     *
     * @return - возвращает количество измененных элементов
     */
    public int getChanged() {
        return changed;
    }

    /**
     * Метод для установки значения полю {@link Info#changed}
     *
     * @param changed - изменено элементов
     */
    public void setChanged(int changed) {
        this.changed = changed;
    }

    /**
     * Метод для получения значения поля {@link Info#deleted}
     *
     * @return - возвращает количество удаленных элементов
     */
    public int getDeleted() {
        return deleted;
    }

    /**
     * Метод для установки значения полю {@link Info#deleted}
     *
     * @param deleted - удалено элементов
     */
    public void setDeleted(int deleted) {
        this.deleted = deleted;
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
        Info info = (Info) o;
        return added == info.added && changed == info.changed && deleted == info.deleted;
    }

    /**
     * Переопределенный метод, для получения числового представления объекта
     *
     * @return - возвращает hashcode объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(added, changed, deleted);
    }

    /**
     * Метод используется для корректоного представления объекта в виде строки
     *
     * @return - строку с информацией об объекте
     */
    @Override
    public String toString() {
        return "Info{"
                + "added=" + added
                + ", changed=" + changed
                + ", deleted=" + deleted
                + '}';
    }
}

package ru.job4j.io.duplicates;

import java.util.Objects;

/**
 * Класс модели файла со свойствами <b>size</b> и <b>name</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class FileProperty {
    /**
     * Поле размер файла
     */
    private long size;
    /**
     * Поле имя файла
     */
    private String name;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param size - размер
     * @param name - имя
     */
    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    /**
     * Метод используется для получения размера файла {@link FileProperty#size}
     *
     * @return - возвращает размер файла
     */
    public long getSize() {
        return size;
    }

    /**
     * Метод используется для установки значения полю {@link FileProperty#size}
     *
     * @param size - размер
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * Метод используется для получения имени файла {@link FileProperty#name}
     *
     * @return - возвращает имя файла
     */
    public String getName() {
        return name;
    }

    /**
     * Метод используется для установки значения полю {@link FileProperty#name}
     *
     * @param name - имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Переопределенный метод, для корректного сравнения объектов
     *
     * @param o - другой файл
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
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    /**
     * Переопределенный метод, для получения числового представления объекта
     *
     * @return - возвращает hashcode объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }
}

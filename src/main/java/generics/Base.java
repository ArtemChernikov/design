package generics;

/**
 * Абстрактный класс описывает модель объекта со свойством <b>id</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public abstract class Base {
    /**
     * Поле id объекта
     */
    private final String id;

    /**
     * Конструктор - создание нового объекта с определенным значением
     *
     * @param id - id объекта
     */
    public Base(final String id) {
        this.id = id;
    }

    /**
     * Метод для получения значения поля {@link Base#id}
     *
     * @return - возвращает id
     */
    public String getId() {
        return id;
    }
}

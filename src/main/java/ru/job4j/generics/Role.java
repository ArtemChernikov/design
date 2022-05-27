package ru.job4j.generics;

/**
 * Класс описывает модель роли со свойством <b>roleName</b>
 * Наследуется от {@link Base}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Role extends Base {
    /**
     * Поле названия роли
     */
    private final String roleName;

    /**
     * Конструктор - создание нового объекта с определенным значением
     *
     * @param id   - id пользователя
     * @param name - название роли
     */
    public Role(String id, String name) {
        super(id);
        this.roleName = name;
    }

    /**
     * Метод для получения значения поля {@link Role#roleName}
     *
     * @return - возвращает название роли
     */
    public String getRoleName() {
        return roleName;
    }
}

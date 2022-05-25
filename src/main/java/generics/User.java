package generics;

/**
 * Класс описывает модель пользователя со свойством <b>username</b>
 * Наследуется от {@link Base}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class User extends Base {
    /**
     * Поле имени пользователя
     */
    private final String username;

    /**
     * Конструктор - создание нового объекта с определенным значением
     *
     * @param id   - id пользователя
     * @param name - имя пользователя
     */
    public User(String id, String name) {
        super(id);
        this.username = name;
    }

    /**
     * Метод для получения значения поля {@link User#username}
     *
     * @return - возвращает имя пользователя
     */
    public String getUsername() {
        return username;
    }
}

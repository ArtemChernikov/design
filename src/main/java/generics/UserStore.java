package generics;

/**
 * Класс описывает модель хранилища со свойством <b>store</b>
 * Имплементирует интерфейс {@link Store}
 * В классе используются идентичные методы {@link MemStore}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class UserStore implements Store<User> {
    /**
     * Поле с объектом {@link MemStore} для использования его методов
     */
    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}

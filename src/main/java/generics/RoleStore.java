package generics;

/**
 * Класс описывает модель хранилища со свойством <b>store</b>
 * Имплементирует интерфейс {@link Store}
 * В классе используются идентичные методы {@link MemStore}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class RoleStore implements Store<Role> {
    /**
     * Поле с объектом {@link MemStore} для использования его методов
     */
    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
}

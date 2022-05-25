package generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс описывает модель хранилища определенных эелементов со свойством <b>username</b>
 * Методы класса в последющем используются в других классах
 * Имплементирует интерфейс {@link Store}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public final class MemStore<T extends Base> implements Store<T> {
    /**
     * Поле хранилища где key - id модели, а value - это сама модель
     */
    private final Map<String, T> storage = new HashMap<>();

    /**
     * Метод выполняет добавление элемента в хранилище, если такой элемент отсутствует
     *
     * @param model - элемент
     */
    @Override
    public void add(T model) {
        if (storage.get(model.getId()) == null) {
            storage.put(model.getId(), model);
        }
    }

    /**
     * Метод выполняет замену элемента в хранилище, если есть совпадение по id
     *
     * @param id    - id элемента
     * @param model - сам элемент
     * @return - возвращает true - если элемент заменен, false - если не заменен
     */
    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        if (storage.containsKey(id)) {
            storage.put(id, model);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод выполняет удаление элемента из хранилища, если он там имеется
     *
     * @param id - id элемента
     * @return - возвращает true - если элемент удален, false - если не удален
     */
    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        if (storage.containsKey(id)) {
            storage.remove(id);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод выполняет поиск элемента в хранилище по id
     *
     * @param id - id элемента
     * @return - возвращает элемент если он найден, и null если он отсутствует
     */
    @Override
    public T findById(String id) {
        return storage.containsKey(id) ? storage.get(id) : null;
    }
}

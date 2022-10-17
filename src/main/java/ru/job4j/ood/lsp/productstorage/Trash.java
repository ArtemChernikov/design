package ru.job4j.ood.lsp.productstorage;

import static ru.job4j.ood.lsp.productstorage.Percent.calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает модель хранилища продуктов (Мусорка)
 * В это хранилище попадают продукты только с израсходованным сроком годности от 100%
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Trash implements Store {
    private final List<Food> storage = new ArrayList<>();

    /**
     * Метод используется для добавления продукта в хранилище
     *
     * @param food - продукт
     * @return - возвращает boolean
     */
    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (calculate(food) == HIGH_EXPIRATION_DATE) {
            storage.add(food);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод используется для возвращения копии хранилища
     *
     * @return - возвращает копию хранилища
     */
    @Override
    public List<Food> getStorage() {
        return new ArrayList<>(storage);
    }
}

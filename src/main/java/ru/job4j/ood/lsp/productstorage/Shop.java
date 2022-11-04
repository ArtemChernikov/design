package ru.job4j.ood.lsp.productstorage;

import static ru.job4j.ood.lsp.productstorage.Percent.calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает модель хранилища продуктов (Магазин)
 * В это хранилище попадают продукты только с израсходованным сроком годности
 * от 25% (включительно) и менее 75%, также попадают продукты с израсходованным сроком
 * годности от 75% и менее 100%, но на них делается скидка
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class Shop implements Store {
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
        int percent = calculate(food);
        if (percent >= LOW_EXPIRATION_DATE && percent < HIGH_EXPIRATION_DATE) {
            if (percent > MID_EXPIRATION_DATE) {
                double price = food.getPrice();
                food.setPrice(price - (price / 100 * food.getDiscount()));
            }
            storage.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void clearStore() {
        storage.clear();
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

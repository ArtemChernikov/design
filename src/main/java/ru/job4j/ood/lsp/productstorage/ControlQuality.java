package ru.job4j.ood.lsp.productstorage;

import java.util.List;

/**
 * Класс описывает модель распределения продуктов по хранилищам
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ControlQuality {
    /**
     * Поле хранилища продуктов
     */
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * Метод используется для распределения продуктов по хранилищам
     *
     * @param food - продукт {@link Food}
     */
    public void distribution(Food food) {
        for (Store store : stores) {
            store.add(food);
        }
    }
}

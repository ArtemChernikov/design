package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает модель распределения продуктов по хранилищам
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
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

    /**
     * Метод используется для пересортировки продуктов, находящихся в хранилищах
     */
    public void resort() {
        List<Food> temp = new ArrayList<>();
        stores.forEach((x) -> {
                    temp.addAll(x.getStorage());
                    x.clearStore();
                }
        );
        temp.forEach(this::distribution);
    }
}

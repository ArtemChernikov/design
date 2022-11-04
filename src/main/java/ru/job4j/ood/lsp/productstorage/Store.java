package ru.job4j.ood.lsp.productstorage;

import java.util.List;

/**
 * Интерфейс описывает модель хранилища продуктов
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public interface Store {
    /**
     * Поле 25% от срока годности
     */
    int LOW_EXPIRATION_DATE = 25;
    /**
     * Поле 75% от срока годности
     */
    int MID_EXPIRATION_DATE = 75;
    /**
     * Поле 100% от срока годности
     */
    int HIGH_EXPIRATION_DATE = 100;

    void clearStore();

    boolean add(Food food);

    List<Food> getStorage();
}

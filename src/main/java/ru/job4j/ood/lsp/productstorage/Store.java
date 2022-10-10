package ru.job4j.ood.lsp.productstorage;

import java.util.List;

public interface Store {

    int LOW_EXPIRATION_DATE = 25;
    int MID_EXPIRATION_DATE = 75;
    int HIGH_EXPIRATION_DATE = 100;

    boolean add(Food food);

    List<Food> getStorage();
}

package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    List<Food> storage = new ArrayList<>();

    @Override
    public void add(Food food) {
        if (food.getCorruptionPercent() < 25) {
            storage.add(food);
        }
    }

    @Override
    public List<Food> getStorage() {
        return storage;
    }
}

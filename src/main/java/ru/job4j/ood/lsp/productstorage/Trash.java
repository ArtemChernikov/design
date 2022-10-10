package ru.job4j.ood.lsp.productstorage;

import static ru.job4j.ood.lsp.productstorage.Percent.calculate;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
   private final List<Food> storage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (calculate(food) == HIGH_EXPIRATION_DATE) {
            storage.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getStorage() {
        return new ArrayList<>(storage);
    }
}

package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    List<Food> storage = new ArrayList<>();

    @Override
    public void add(Food food) {
        int percent = food.getCorruptionPercent();
        if (percent >= 25 && percent <= 75) {
            storage.add(food);
        } else if (percent > 75 && percent != 100) {
            double price = food.getPrice();
            double newPrice = price - (price / 100 * food.getDiscount());
            food.setPrice(newPrice);
            storage.add(food);
        }
    }

    @Override
    public List<Food> getStorage() {
        return storage;
    }
}

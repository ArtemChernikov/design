package ru.job4j.ood.lsp.productstorage;

import static ru.job4j.ood.lsp.productstorage.Percent.calculate;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
   private final List<Food> storage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        int percent = calculate(food);
        if (percent >= LOW_EXPIRATION_DATE && percent <= MID_EXPIRATION_DATE) {
            storage.add(food);
            rsl = true;
        } else if (percent > MID_EXPIRATION_DATE && percent != HIGH_EXPIRATION_DATE) {
            double price = food.getPrice();
            food.setPrice(price - (price / 100 * food.getDiscount()));
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

package ru.job4j.ood.lsp.productstorage;

import java.time.LocalDate;

public class Meat extends Food {
    public Meat(String name, LocalDate expiryDate, LocalDate createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}

package ru.job4j.ood.lsp.productstorage;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price;
    private final int discount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        validate(expiryDate, createDate, price, discount);
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    private void validate(LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        if (expiryDate.isBefore(createDate) || expiryDate.equals(createDate)) {
            throw new IllegalArgumentException("Укажите корректную дату окончания срока годности продукта!");
        }
        if (discount > 99 || discount < 1) {
            throw new IllegalArgumentException("Укажите корректную скидку в % на продукт!");
        }
        if (price < 1) {
            throw new IllegalArgumentException("Укажите корректную цену на продукт!");
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return price == food.price && discount == food.discount && Objects.equals(name, food.name)
                && Objects.equals(expiryDate, food.expiryDate) && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}

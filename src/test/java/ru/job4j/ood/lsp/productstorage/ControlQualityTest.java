package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControlQualityTest {
    @Test
    void whenMeatExpired() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        Food meat = new Meat("pork", LocalDate.of(2022, 10, 6),
                LocalDate.of(2022, 10, 1), 55, 10);
        controlQuality.distribution(meat);
        assertThat(trash.getStorage().get(0)).isEqualTo(meat);
    }

    @Test
    void whenFruitGetsIntoShop() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        Food apple = new Fruit("apple", LocalDate.of(2022, 10, 15),
                LocalDate.of(2022, 10, 1), 55, 10);
        controlQuality.distribution(apple);
        assertThat(shop.getStorage().get(0)).isEqualTo(apple);
    }

    @Test
    void whenFruitGetsIntoShopAndDiscount() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        Food apple = new Fruit("apple", LocalDate.of(2022, 10, 8),
                LocalDate.of(2022, 9, 1), 50, 10);
        controlQuality.distribution(apple);
        double expectPrice = 45;
        assertThat(shop.getStorage().get(0).getPrice()).isEqualTo(expectPrice);
    }

    @Test
    void whenMeatGetsIntoWarehouse() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        Food pork = new Meat("pork", LocalDate.of(2022, 10, 29),
                LocalDate.of(2022, 10, 1), 50, 10);
        controlQuality.distribution(pork);
        assertThat(warehouse.getStorage().get(0)).isEqualTo(pork);
    }

    @Test
    void whenCreateFoodWithIncorrectDate() {
        assertThrows(IllegalArgumentException.class, () -> new Meat("pork", LocalDate.of(2022, 10, 1),
                LocalDate.of(2022, 10, 5), 50, 10));

    }

    @Test
    void whenCreateFoodWithIncorrectDiscount() {
        assertThrows(IllegalArgumentException.class, () -> new Meat("pork", LocalDate.of(2022, 10, 29),
                LocalDate.of(2022, 10, 5), 50, 150));

    }

    @Test
    void whenCreateFoodWithIncorrectPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Meat("pork", LocalDate.of(2022, 10, 29),
                LocalDate.of(2022, 10, 5), 0, 10));

    }
}
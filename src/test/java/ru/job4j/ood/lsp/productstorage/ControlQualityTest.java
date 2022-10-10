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
        LocalDate now = LocalDate.now();
        Food meat = new Meat("pork", now.minusDays(4),
                now.minusDays(9), 55, 10);
        controlQuality.distribution(meat);
        assertThat(trash.getStorage().get(0)).isEqualTo(meat);
    }

    @Test
    void whenFruitGetsIntoShop() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        LocalDate now = LocalDate.now();
        Food apple = new Fruit("apple", now.plusDays(5),
                now.minusDays(9), 55, 10);
        controlQuality.distribution(apple);
        assertThat(shop.getStorage().get(0)).isEqualTo(apple);
    }

    @Test
    void whenFruitGetsIntoShopAndDiscount() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        LocalDate now = LocalDate.now();
        Food apple = new Fruit("apple", now.plusDays(2),
                now.minusDays(30), 50, 10);
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
        LocalDate now = LocalDate.now();
        Food pork = new Meat("pork", now.plusDays(45),
                now.minusDays(9), 50, 10);
        controlQuality.distribution(pork);
        assertThat(warehouse.getStorage().get(0)).isEqualTo(pork);
    }

    @Test
    void whenDifferentProductsIntoDifferentStores() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        LocalDate now = LocalDate.now();
        double expectPrice = 45;
        Food pork = new Meat("pork", now.plusDays(45),
                now.minusDays(9), 50, 10);
        Food apple = new Fruit("apple", now.plusDays(2),
                now.minusDays(30), 50, 10);
        Food pineapple = new Fruit("pineapple", now.plusDays(5),
                now.minusDays(9), 55, 10);
        Food chicken = new Meat("chicken", now.minusDays(4),
                now.minusDays(9), 55, 10);
        controlQuality.distribution(pork);
        controlQuality.distribution(apple);
        controlQuality.distribution(pineapple);
        controlQuality.distribution(chicken);
        assertThat(warehouse.getStorage().get(0)).isEqualTo(pork);
        assertThat(shop.getStorage().get(0).getPrice()).isEqualTo(expectPrice);
        assertThat(shop.getStorage().get(1)).isEqualTo(pineapple);
        assertThat(trash.getStorage().get(0)).isEqualTo(chicken);
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
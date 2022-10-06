package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    @Test
    void whenMeatExpired() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        Food meat = new Food("pork", LocalDate.of(2022, 10, 6),
                LocalDate.of(2022, 10, 1), 55, 10);
        controlQuality.distribution(meat);
        assertThat(meat).isEqualTo(trash.getStorage().get(0));
    }
}
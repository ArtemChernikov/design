package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(menu.select("Сходить в магазин").get())
                .isEqualTo(new Menu.MenuItemInfo("Сходить в магазин",
                        List.of("Купить продукты"), STUB_ACTION, "1."));
        assertThat(menu.select("Купить продукты").get())
                .isEqualTo(new Menu.MenuItemInfo(
                        "Купить продукты",
                        List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."));

        assertThat(menu.select("Покормить собаку").get()).isEqualTo(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."));
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenPrintMenu() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsoleMenuPrinter();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        printer.print(menu);
        String expect = """
                1.Сходить в магазин
                --1.1.Купить продукты
                ----1.1.1.Купить хлеб
                ----1.1.2.Купить молоко
                2.Покормить собаку
                """;
        assertThat(output.toString()).isEqualToNormalizingNewlines(expect);
    }

    @Test
    public void whenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.select("Купить продукты");
        assertThat(new Menu.MenuItemInfo("Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
    }
}
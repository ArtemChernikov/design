package ru.job4j.ood.isp.menu;

/**
 * Добавляем отступ только к вложенным меню
 */
public class ConsoleMenuPrinter implements MenuPrinter {
    private static final String INDENT = "--";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            StringBuilder buffer = new StringBuilder();
            String number = item.getNumber();
            buffer.append(INDENT.repeat(Math.max(0, number.split("\\.").length - 1)));
            buffer.append(number);
            System.out.println(buffer.append(item.getName()));
        }
    }
}

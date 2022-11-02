package ru.job4j.ood.isp.menu;

/**
 * Добавляем отступ только к вложенным меню
 */
public class Printer implements MenuPrinter {
    public static final String INDENT = "--";
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo m : menu) {
            StringBuilder sb = new StringBuilder();
            String number = m.getNumber();
            sb.append(INDENT.repeat(Math.max(0, number.split("\\.").length - 1)));
            sb.append(number);
            System.out.println(sb.append(m.getName()));
        }
    }
}

package ru.job4j.ood.lsp.productstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Класс описывает модель вычисления испорченности продукта в процентах
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Percent {
    /**
     * Метод используется для вычисления испорченности продукта в процентах
     * 1) Находим разницу между конечной датой срока годности и нынешней датой
     * 2) Умножаем на 100 полученный результат
     * 3) Делим на количество дней (срок хранения продукта)
     * 4) Округляем полученный результат и получаем остаток дней до
     * конца срока годности от даты производства в процентах
     * 5) Отнимаем полученный результат от 100 и получаем израсходованный срок годности в процентах
     *
     * @param expireDate - дата конца срока годности продукта {@link Food}
     * @param createDate - дата производства продукта {@link Food}
     * @return - возвращает процент израсходованности
     */
    public static int calculate(LocalDate expireDate, LocalDate createDate) {
        LocalDate now = LocalDate.now();
        int percent = 100 - Math.round(now.until(expireDate, ChronoUnit.DAYS) * 100
                / (float) createDate.until(expireDate, ChronoUnit.DAYS));
        return Math.min(percent, 100);
    }
}

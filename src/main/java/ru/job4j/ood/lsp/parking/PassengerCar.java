package ru.job4j.ood.lsp.parking;

/**
 * Класс описывает модель легковой машины наследуется от {@link Car}
 * Размер легковой машины всегда равен 1.
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class PassengerCar extends Car {
    /**
     * Поле размер легковой машины
     */
    public static final int SIZE = 1;

    public PassengerCar(String brand, String model, String color, String number) {
        super(brand, model, color, number, SIZE);
    }
}

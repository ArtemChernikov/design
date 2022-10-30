package ru.job4j.ood.lsp.parking;

/**
 * Класс описывает модель грузовой машины наследуется от {@link Car}
 * Размер грузовой машины всегда больше 1.
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Truck extends Car {
    public Truck(String brand, String model, String color, String number, int size) {
        super(brand, model, color, number, size);
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException("Размер грузового автомобиля должен быть больше 1!");
        }
    }
}

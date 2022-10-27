package ru.job4j.ood.lsp.parking;

public class Truck extends Car {

    public Truck(String brand, String model, String color, String number, int size) {
        super(brand, model, color, number, size);
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException("Размер грузового автомобиля должен быть больше 1!");
        }
    }

}

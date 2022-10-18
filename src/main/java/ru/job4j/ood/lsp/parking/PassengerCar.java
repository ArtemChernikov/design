package ru.job4j.ood.lsp.parking;

public class PassengerCar extends Car {
    final public int size = 1;

    public PassengerCar(String brand, String model, String color, String number) {
        super(brand, model, color, number);
    }

    public int getSize() {
        return size;
    }
}

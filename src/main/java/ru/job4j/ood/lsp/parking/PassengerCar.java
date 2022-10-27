package ru.job4j.ood.lsp.parking;

public class PassengerCar extends Car {
    static final public int SIZE = 1;

    public PassengerCar(String brand, String model, String color, String number) {
        super(brand, model, color, number, SIZE);
    }

//    public int getSize() {
//        return SIZE;
//    }
}

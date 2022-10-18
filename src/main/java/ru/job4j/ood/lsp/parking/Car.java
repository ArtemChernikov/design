package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public abstract class Car {

    private final String brand;
    private final String model;
    private String color;
    private String number;

    private int size;

    public Car(String brand, String model, String color, String number) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.number = number;
    }

    public String getModel() {
        return model;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, number, size);
    }

    @Override
    public String toString() {
        return "Car{"
                + "brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", color='" + color + '\''
                + ", number='" + number + '\''
                + ", size=" + size
                + '}';
    }
}

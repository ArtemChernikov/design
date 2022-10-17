package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public abstract class Car {
    private final String model;
    private String color;
    private final int size;

    public Car(String model, String color, int size) {
        this.model = model;
        this.color = color;
        this.size = size;
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

    public int getSize() {
        return size;
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
        return size == car.size && Objects.equals(model, car.model) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, color, size);
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", color='" + color + '\''
                + ", size=" + size
                + '}';
    }
}

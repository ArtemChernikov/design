package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
class CarParkingTest {

    @Test
    void when2PassengerCarAnd1TruckParking() {
        CarParking carParking = new CarParking(2, 1);
        Car car1 = new PassengerCar("Volvo", "Q-60", "Yellow", "UC854RE");
        Car car2 = new PassengerCar("Skoda", "Rapid", "White", "FC456SC");
        Car truck1 = new Truck("Volvo", "Truck-40", "Black", "UC782FG", 2);
        carParking.addCar(car1);
        carParking.addCar(car2);
        carParking.addCar(truck1);
        assertThat(carParking.getCarParking()).contains(car1);
        assertThat(carParking.getCarParking()).contains(car2);
        assertThat(carParking.getTruckParking()).contains(truck1);
    }

    @Test
    void when2TruckParking() {
        CarParking carParking = new CarParking(2, 1);
        Car truck1 = new Truck("Mercedes", "SLC-SS", "Red", "GB463GE", 4);
        Car truck2 = new Truck("Volvo", "Truck-40", "Black", "UC782FG", 2);
        carParking.addCar(truck1);
        carParking.addCar(truck2);
        assertThat(carParking.getTruckParking()).contains(truck1);
        assertThat(carParking.getCarParking()).contains(truck2);
    }
}
package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class CarParkingTest {

    @Test
    void when2PassengerCarAnd1TruckParking() {
        CarParking carParking = new CarParking(2, 1);
        Car car1 = new PassengerCar("Volvo", "Q-60", "Yellow", "UC854RE");
        Car car2 = new PassengerCar("Skoda", "Rapid", "White", "FC456SC");
        Car truck1 = new Truck("Volvo", "Truck-40", "Black", "UC782FG", 2);
        assertTrue(carParking.addCar(car1));
        assertTrue(carParking.addCar(car2));
        assertTrue(carParking.addCar(truck1));
    }

    @Test
    void when2TruckParking() {
        CarParking carParking = new CarParking(2, 1);
        Car truck1 = new Truck("Mercedes", "SLC-SS", "Red", "GB463GE", 4);
        Car truck2 = new Truck("Volvo", "Truck-40", "Black", "UC782FG", 2);
        assertTrue(carParking.addCar(truck1));
        assertTrue(carParking.addCar(truck2));
    }

    @Test
    void when2TruckAnd2PassengerCarParking() {
        CarParking carParking = new CarParking(2, 1);
        Car truck1 = new Truck("Mercedes", "SLC-SS", "Red", "GB463GE", 4);
        Car truck2 = new Truck("Volvo", "Truck-40", "Black", "UC782FG", 2);
        Car car1 = new PassengerCar("Volvo", "Q-60", "Yellow", "UC854RE");
        Car car2 = new PassengerCar("Skoda", "Rapid", "White", "FC456SC");
        assertTrue(carParking.addCar(truck1));
        assertTrue(carParking.addCar(car1));
        assertTrue(carParking.addCar(truck2));
        assertTrue(carParking.addCar(car2));
    }

    @Test
    void whenNoSpaceFor1CarOnTruckParking() {
        CarParking carParking = new CarParking(1, 2);
        Car truck1 = new Truck("Mercedes", "SLC-SS", "Red", "GB463GE", 4);
        Car truck2 = new Truck("Volvo", "Truck-40", "Black", "UC782FG", 4);
        Car car1 = new PassengerCar("Volvo", "Q-60", "Yellow", "UC854RE");
        Car car2 = new PassengerCar("Skoda", "Rapid", "White", "FC456SC");
        assertTrue(carParking.addCar(truck1));
        assertTrue(carParking.addCar(truck2));
        assertTrue(carParking.addCar(car1));
        assertFalse(carParking.addCar(car2));
    }

    @Test
    void whenNoSpaceFor1TruckOnPassengerCarParking() {
        CarParking carParking = new CarParking(2, 1);
        Car truck1 = new Truck("Mercedes", "SLC-SS", "Red", "GB463GE", 4);
        Car truck2 = new Truck("Volvo", "Truck-40", "Black", "UC782FG", 4);
        assertTrue(carParking.addCar(truck1));
        assertFalse(carParking.addCar(truck2));
    }

    @Test
    void whenCreateTruckWithSizeLess1() {
        assertThrows(IllegalArgumentException.class, () -> new Truck("Mercedes", "SLC-SS",
                "Red", "GB463GE", 4));
    }

}
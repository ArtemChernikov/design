package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

public class CarParking implements Parking {
    private final Set<Car> carParking;
    private final Set<Car> truckParking;
    private int freeCarParkingSpaces;
    private int freeTruckParkingSpaces;
    private int occupiedCarParkingSpaces = 0;
    private int occupiedTruckParkingSpaces = 0;


    public CarParking(int carParkingSpaces, int truckParkingSpaces) {
        freeCarParkingSpaces = carParkingSpaces;
        freeTruckParkingSpaces = truckParkingSpaces;
        carParking = new HashSet<>(carParkingSpaces);
        truckParking = new HashSet<>(truckParkingSpaces);
    }

    @Override
    public boolean addCar(Car car) {
        boolean rsl = false;
        int carSize = car.getSize();
        if (!carParking.contains(car) && !truckParking.contains(car)) {
            if (carSize == PassengerCar.SIZE && freeCarParkingSpaces > 0) {
                carParking.add(car);
                freeCarParkingSpaces -= PassengerCar.SIZE;
                occupiedCarParkingSpaces += PassengerCar.SIZE;
                rsl = true;
            } else if (carSize > PassengerCar.SIZE && freeTruckParkingSpaces > 0) {
                truckParking.add(car);
                freeTruckParkingSpaces -= PassengerCar.SIZE;
                occupiedTruckParkingSpaces += PassengerCar.SIZE;
                rsl = true;
            } else if (freeCarParkingSpaces >= carSize) {
                carParking.add(car);
                freeCarParkingSpaces -= carSize;
                occupiedCarParkingSpaces += carSize;
                rsl = true;
            }
        }
        return rsl;
    }
}

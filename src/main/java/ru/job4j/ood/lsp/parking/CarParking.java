package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

public class CarParking implements Parking {
    private final Set<Car> carParking = new HashSet<>();
    private final Set<Car> truckParking = new HashSet<>();
    private final int carParkingSpaces;
    private final int truckParkingSpaces;
    private int freeCarParkingSpaces;
    private int freeTruckParkingSpaces;
    private int occupiedCarParkingSpaces = 0;
    private int occupiedTruckParkingSpaces = 0;


    public CarParking(int carParkingSpaces, int truckParkingSpaces) {
        this.carParkingSpaces = carParkingSpaces;
        this.truckParkingSpaces = truckParkingSpaces;
        freeCarParkingSpaces = carParkingSpaces;
        freeTruckParkingSpaces = truckParkingSpaces;
    }

    @Override
    public boolean addCar(Car car) {
        return false;
    }

    @Override
    public boolean removeCar() {
        return false;
    }


    public int getCarParkingSpaces() {
        return carParkingSpaces;
    }

    public int getTruckParkingSpaces() {
        return truckParkingSpaces;
    }

    public int getFreeCarParkingSpaces() {
        return freeCarParkingSpaces;
    }

    public void setFreeCarParkingSpaces(int freeCarParkingSpaces) {
        this.freeCarParkingSpaces = freeCarParkingSpaces;
    }

    public int getFreeTruckParkingSpaces() {
        return freeTruckParkingSpaces;
    }

    public void setFreeTruckParkingSpaces(int freeTruckParkingSpaces) {
        this.freeTruckParkingSpaces = freeTruckParkingSpaces;
    }

    public int getOccupiedCarParkingSpaces() {
        return occupiedCarParkingSpaces;
    }

    public void setOccupiedCarParkingSpaces(int occupiedCarParkingSpaces) {
        this.occupiedCarParkingSpaces = occupiedCarParkingSpaces;
    }

    public int getOccupiedTruckParkingSpaces() {
        return occupiedTruckParkingSpaces;
    }

    public void setOccupiedTruckParkingSpaces(int occupiedTruckParkingSpaces) {
        this.occupiedTruckParkingSpaces = occupiedTruckParkingSpaces;
    }

    public Set<Car> getCarParking() {
        return new HashSet<>(carParking);
    }

    public Set<Car> getTruckParking() {
        return new HashSet<>(truckParking);
    }
}

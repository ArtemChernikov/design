package ru.job4j.ood.lsp.parking;

public abstract class CarParking implements Parking {
    private final int parkingSpaces;
    private int freeParkingSpaces;
    private int occupiedParkingSpaces = 0;


    public CarParking(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
        freeParkingSpaces = parkingSpaces;
    }

    @Override
    public boolean addCar(Car car) {
        return false;
    }

    @Override
    public boolean removeCar() {
        return false;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public int getFreeParkingSpaces() {
        return freeParkingSpaces;
    }

    public void setFreeParkingSpaces(int freeParkingSpaces) {
        this.freeParkingSpaces = freeParkingSpaces;
    }

    public int getOccupiedParkingSpaces() {
        return occupiedParkingSpaces;
    }

    public void setOccupiedParkingSpaces(int occupiedParkingSpaces) {
        this.occupiedParkingSpaces = occupiedParkingSpaces;
    }
}

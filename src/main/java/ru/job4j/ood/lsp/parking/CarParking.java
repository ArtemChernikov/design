package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс описывает модель учета на парковке для машин.
 * На парковке отдельное количество мест для легковых машин
 * и отдельное количество мест для грузовых машин
 * (размер ячейки для легковой машины 1; размер ячейки для грузовой <1).
 * Легковая машина не может встать на место для грузовой, а грузовая может
 * на несколько мест легковых машин, позволяет наличие свободных мест.
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class CarParking implements Parking {
    /**
     * Поле места для легковых машин
     */
    private final Set<Car> carParking;
    /**
     * Поле места для грузовых машин
     */
    private final Set<Car> truckParking;
    /**
     * Поле свободные места для легковых машин на парковке
     */
    private int freeCarParkingSpaces;
    /**
     * Поле свободные места для грузовых машин на парковке
     */
    private int freeTruckParkingSpaces;
    /**
     * Поле занятые места для легковых машин на парковке
     */
    private int occupiedCarParkingSpaces = 0;
    /**
     * Поле занятые места для легковых машин на парковке
     */
    private int occupiedTruckParkingSpaces = 0;


    public CarParking(int carParkingSpaces, int truckParkingSpaces) {
        freeCarParkingSpaces = carParkingSpaces;
        freeTruckParkingSpaces = truckParkingSpaces;
        carParking = new HashSet<>(carParkingSpaces);
        truckParking = new HashSet<>(truckParkingSpaces);
    }

    /**
     * Метод используется для добавления машины на парковку.
     * Машина ставится на свое место в зависимости от размера (легковая или грузовая)
     *
     * @param car - машина
     * @return - возвращает boolean
     */
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

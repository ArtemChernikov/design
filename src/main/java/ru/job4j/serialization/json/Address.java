package ru.job4j.serialization.json;

/**
 * Класс описывает модель адреса со свойствами <b>city</b>, <b>street</b>, <b>house</b> и <b>apartment</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class Address {
    /**
     * Поле город
     */
    private final String city;
    /**
     * Поле улица
     */
    private final String street;
    /**
     * Поле дом
     */
    private final int house;
    /**
     * Поле квартира
     */
    private final int apartment;

    /**
     * Конструктор - создание объекта с определенными свойствами
     *
     * @param city      - город
     * @param street    - улица
     * @param house     - дом
     * @param apartment - квартира
     */
    public Address(String city, String street, int house, int apartment) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    /**
     * Метод используется для получения поля {@link Address#city}
     *
     * @return - возвращает город
     */
    public String getCity() {
        return city;
    }

    /**
     * Метод используется для получения поля {@link Address#street}
     *
     * @return - возвращает улицу
     */
    public String getStreet() {
        return street;
    }

    /**
     * Метод используется для получения поля {@link Address#house}
     *
     * @return - возвращает номер дома
     */
    public int getHouse() {
        return house;
    }

    /**
     * Метод используется для получения поля {@link Address#apartment}
     *
     * @return - возвращает номер квартиры
     */
    public int getApartment() {
        return apartment;
    }

    /**
     * Метод переопределяется для наилучшего вывода объекта и его свойств в консоль
     *
     * @return - возвращает объект в виде строки
     */
    @Override
    public String toString() {
        return "Address{"
                + "city='" + city + '\''
                + ", street='" + street + '\''
                + ", house=" + house
                + ", apartment=" + apartment
                + '}';
    }
}

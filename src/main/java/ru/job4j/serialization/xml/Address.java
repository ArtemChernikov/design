package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс описывает модель адреса со свойствами <b>city</b>, <b>street</b>, <b>house</b> и <b>apartment</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
@XmlRootElement(name = "contact")
public class Address {
    /**
     * Поле город
     */
    @XmlAttribute
    private String city;
    /**
     * Поле улица
     */
    @XmlAttribute
    private String street;
    /**
     * Поле дом
     */
    @XmlAttribute
    private int house;
    /**
     * Поле квартира
     */
    @XmlAttribute
    private int apartment;

    /**
     * Дефолтный конструктор
     */
    public Address() {

    }

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

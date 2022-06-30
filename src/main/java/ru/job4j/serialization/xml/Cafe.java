package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;


/**
 * Класс описывает модель кафе со свойствами <b>name</b>, <b>address</b>, <b>open</b>, <b>tables</b> и <b>menu</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
@XmlRootElement(name = "cafe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cafe {
    /**
     * Поле название
     */
    @XmlAttribute
    private String name;
    /**
     * Поле адрес
     */

    private Address address;
    /**
     * Поле кафе открыто
     */
    @XmlAttribute
    private boolean open;
    /**
     * Поле количество столиков
     */
    @XmlAttribute
    private int tables;
    /**
     * Поле меню
     */
    @XmlElementWrapper(name = "dishes")
    @XmlElement(name = "dish")
    private String[] dishes;

    /**
     * Дефолтный конструктор
     */
    public Cafe() {

    }

    /**
     * Конструктор - создание объекта с определенными значениями
     *
     * @param name    - название
     * @param address - адрес
     * @param open    - открыто ли кафе
     * @param tables  - количество столиков
     * @param dishes  - блюда
     */
    public Cafe(String name, Address address, boolean open, int tables, String[] dishes) {
        this.name = name;
        this.address = address;
        this.open = open;
        this.tables = tables;
        this.dishes = dishes;
    }

    /**
     * Метод переопределяется для наилучшего вывода объекта и его свойств в консоль
     *
     * @return - возвращает объект в виде строки
     */
    @Override
    public String toString() {
        return "Cafe{"
                + "name='" + name + '\''
                + ", address=" + address
                + ", open=" + open
                + ", tables=" + tables
                + ", dishes=" + Arrays.toString(dishes)
                + '}';
    }
}

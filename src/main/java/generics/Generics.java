package generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Класс описывает применение Generics, для ограничения передаваемого типа данных в методы
 * Используются классы {@link Animal}, {@link Predator} и {@link Tiger}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        gen.printBoundedWildCard(second);
        System.out.println();

        gen.printLowerBoundedWildCard(second);
    }

    /**
     * Метод описывает итерации и вывод на печать каждого элемента коллекции
     * Метод принимает коллекцию с любым типом данных
     *
     * @param list - список с WildCard
     */
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Метод описывает итерации и вывод на печать каждого элемента коллекции
     * Метод принимает коллекцию типа {@link Predator} или его наследников
     *
     * @param list - список с Bounded WildCard
     */
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Метод описывает итерации и вывод на печать каждого элемента коллекции
     * Метод принимает коллекцию типа {@link Predator} или его родителей
     *
     * @param list - список с Lower bounded WildCard
     */
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}

package ru.job4j.design.violationsrp;

import java.util.List;
import java.util.Set;

/**
 * Класс описывает намеренное нарушение первого принципа SOLID
 * SRP (Single Responsibility Principle), для демонстрации
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Search {
    public void searchInList(List<String> list, String str) {
        System.out.println("Выполняется поиск в списке!");
    }

    public void searchInMap(List<String> list, String str) {
        System.out.println("Выполняется поиск в карте!");
    }

    public void searchInSet(Set<String> set, String str) {
        System.out.println("Выполняется поиск во множестве!");
    }

    public void searchInDB(String str) {
        System.out.println("Выполняется поиск в базе данных!");
    }
}

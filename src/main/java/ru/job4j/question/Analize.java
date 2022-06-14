package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Класс описывает модель анализа изменений двух множеств
 * В класее используются {@link User} и {@link Info}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Analize {
    /**
     * Метод используется для определения изменений в конечном множестве по сравнению с предыдущим
     *
     * @param previous - изначальное множество
     * @param current  - множество после изменений
     * @return - возвращает объект {@link Info} с количеством изменений
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;
        Iterator<User> prev = previous.iterator();
        Iterator<User> cur = current.iterator();
        Map<Integer, String> map = new HashMap<>();
        while (cur.hasNext()) {
            User next = cur.next();
            map.put(next.getId(), next.getName());
        }
        while (prev.hasNext()) {
            User next = prev.next();
            if (!map.containsKey(next.getId()) && !map.containsValue(next.getName())) {
                deleted++;
            }
            if (map.containsKey(next.getId()) && !map.containsValue(next.getName())) {
                changed++;
            }
        }
        added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}

package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс описывает модель коллекции HashMap (без разрешения проблем коллизий) со свойствами <b>LOAD_FACTOR</b>,
 * <b>capacity</b>, <b>count</b>, <b>modCount</b> и <b>table</b>
 * Класс реализовывает интерфейс {@link SimpleMap}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SimpleMap<K, V> implements Map<K, V> {
    /**
     * Поле загруженность коллекции
     */
    private static final float LOAD_FACTOR = 0.75f;
    /**
     * Поле изначальная вместимость
     */
    private int capacity = 8;
    /**
     * Поле счетчик элементов
     */
    private int count = 0;
    /**
     * Поле счетчик модификаций коллекции (удаление и добавление элементов)
     */
    private int modCount = 0;
    /**
     * Поле внутренний массив коллекции
     */
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод используется для добавления элементов в коллекцию
     * Добавление элемента не выполняется, если ячейка уже занята другим элементом
     * Если количество элементов превышает или равно степени загруженности {@link SimpleMap#LOAD_FACTOR},
     * то выполняется увеличение внутреннего массива с помощью метода {@link SimpleMap#expand()}
     *
     * @param key   - ключ
     * @param value - значение
     * @return - возвращает true - если элемент успешно добавлен и false - если наоборот
     */
    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if ((float) (count / capacity) >= LOAD_FACTOR) {
            expand();
        }
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод используется для вычисления хэш-кода на основе хэш-кода объекта
     *
     * @param hashCode - хэш-код объекта
     * @return - возвращает хэш-код
     */
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    /**
     * Метод используется для вычисления индекса на основе хэш-кода, куда будет расположен элемент в коллекции
     *
     * @param hash - хэш-код объекта
     * @return - возвращает индекс массива
     */
    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    /**
     * Метод используется для увеличения вместимости внутреннего массива коллекции в два раза
     * путем создания нового массива и рехеширования каждого элемента
     */
    private void expand() {
        int newSize = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newSize];
        capacity = newSize;
        for (MapEntry<K, V> element : table) {
            if (element != null) {
                newTable[indexFor(hash(element.key.hashCode()))] = element;
            }
        }
        table = newTable;
    }

    /**
     * Метод используется для получения значения по ключу из коллекции
     *
     * @param key - ключ
     * @return - возвращает значение по ключу или null, если такой ключ отсутствует
     */
    @Override
    public V get(K key) {
        V rsl = null;
        MapEntry<K, V> el = table[indexFor(hash(key.hashCode()))];
        if (el != null && el.key.equals(key)) {
            rsl = el.value;
        }
        return rsl;
    }

    /**
     * Метод используется для удаления элемента коллекции по ключу
     *
     * @param key - ключ
     * @return - возвращает true - если элемент успешно удален и false - если наоборот
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> el = table[index];
        if (el != null && el.key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * Анонимный класс {@link Iterator} для корректного перебора элементов коллекции
     *
     * @return - возвращает итератор
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            /**
             * Поле счетчик изменений для недопущения изменений в коллекции во время работы итератора
             */
            private final int expectedModCount = modCount;
            /**
             * Поле индекс
             */
            private int iteratorIndex = 0;

            /**
             * Метод позволяет узнать, есть ли следующий элемент в коллекции
             * 1) Проверяем изменилась ли наша коллекция за время работы итератора
             * если изменилась, то выбрасывается исключение {@link ConcurrentModificationException}
             * @return - true - если следующий элемент есть, false - если нет
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (iteratorIndex < table.length && table[iteratorIndex] == null) {
                    iteratorIndex++;
                }
                return iteratorIndex < table.length - 1;
            }

            /**
             * Метод используется для передвижения указателя итератора по коллекции
             * и возврата соответствующего ключа
             * 1) Проверяем методом {@link Iterator#hasNext()} есть ли следующий элемент в коллекции
             * если нет, то выбрасывается {@link NoSuchElementException}
             * 2) Возвращаем следующий ключ коллекции
             *
             * @return - возвращаем следующий ключ коллекции
             */
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[iteratorIndex++].key;
            }
        };
    }

    /**
     * Приватный статический вложенный класс для хранения пар
     * ключ-значение в коллекции со свойствами <b>key</b> и <b>value</b>
     *
     * @param <K> - ключ элемента
     * @param <V> - значение элемента
     */
    private static class MapEntry<K, V> {
        /**
         * Поле ключ
         */
        K key;
        /**
         * Поле значение
         */
        V value;

        /**
         * Конструктор - создание нового объекта с определенными значениями
         *
         * @param key   - ключ
         * @param value - значение
         */
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}

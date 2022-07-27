package ru.job4j.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleMapTest {
    SimpleMap<Integer, String> map = new SimpleMap<>();

    @Test
    public void whenPutOneElement() {
        Assertions.assertTrue(map.put(1, "Artem"));
    }

    @Test
    public void whenPutElementAndIndexAlreadyTaken() {
        map.put(1, "Artem");
        Assertions.assertFalse(map.put(1, "Artem"));
    }

    @Test
    public void whenPutElementNeedExpand() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        map.put(23, "Fedor");
        map.put(42, "Evgeniy");
        map.put(11, "Jordan");
        map.put(25, "Maxim");
        Assertions.assertTrue(map.put(22, "Maxim"));
    }

    @Test
    public void whenPutDuplicateElement() {
        map.put(2, "Artem");
        Assertions.assertFalse(map.put(2, "Artem"));
    }

    @Test
    public void whenGetElement() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        String expected = "Michail";
        assertThat(expected, is(map.get(44)));
    }

    @Test
    public void whenNotExistElementGet() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        Assertions.assertNull(map.get(22));
    }

    @Test
    public void whenRemoveAndGetElement() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        map.remove(2);
        Assertions.assertNull(map.get(2));
    }

    @Test
    public void whenRemoveElement() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        Assertions.assertTrue(map.remove(2));
    }

    @Test
    public void whenRemoveNotExistElement() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        Assertions.assertFalse(map.remove(3));
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        Assertions.assertFalse(map.iterator().hasNext());
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        assertThrows(NoSuchElementException.class, () -> map.iterator().next());
    }

    @Test
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = map.iterator();
        map.put(44, "Michail");
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }
}
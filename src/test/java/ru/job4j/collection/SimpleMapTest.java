package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleMapTest {
    SimpleMap<Integer, String> map = new SimpleMap<>();

    @Test
    public void whenPutOneElement() {
        assertTrue(map.put(1, "Artem"));
    }

    @Test
    public void whenPutElementAndIndexAlreadyTaken() {
        map.put(1, "Artem");
        assertFalse(map.put(1, "Artem"));
    }

    @Test
    public void whenPutElementNeedExpand() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        map.put(23, "Fedor");
        map.put(42, "Evgeniy");
        map.put(11, "Jordan");
        map.put(25, "Maxim");
        assertTrue(map.put(543, "Maxim"));
    }

    @Test
    public void whenPutDuplicateElement() {
        map.put(2, "Artem");
        assertFalse(map.put(2, "Artem"));
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
        assertNull(map.get(22));
    }

    @Test
    public void whenRemoveAndGetElement() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        map.remove(2);
        assertNull(map.get(2));
    }

    @Test
    public void whenRemoveElement() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        assertTrue(map.remove(2));
    }

    @Test
    public void whenRemoveNotExistElement() {
        map.put(2, "Artem");
        map.put(44, "Michail");
        assertFalse(map.remove(3));
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        Assert.assertFalse(map.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = map.iterator();
        map.put(44, "Michail");
        iterator.next();
    }
}
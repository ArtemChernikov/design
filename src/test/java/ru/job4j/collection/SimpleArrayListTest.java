package ru.job4j.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleArrayListTest {
    SimpleList<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        Assertions.assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(Integer.valueOf(2), list.remove(1));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void whenRemoveByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        Assertions.assertEquals(Integer.valueOf(1), list.get(0));
        Assertions.assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        Assertions.assertEquals(2, list.size());
        Assertions.assertNull(list.get(0));
        Assertions.assertNull(list.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        Assertions.assertEquals(Integer.valueOf(2), list.set(1, 22));
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void whenSetByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, 22));
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        Assertions.assertFalse(list.iterator().hasNext());
    }

    @Test
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        list = new SimpleArrayList<>(5);
        assertThrows(NoSuchElementException.class, () -> list.iterator().next());
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Assertions.assertEquals(Integer.valueOf(1), list.iterator().next());
        Assertions.assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(Integer.valueOf(1), iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(Integer.valueOf(2), iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(Integer.valueOf(3), iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> list.add(v));
    }

    @Test
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(0);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }
}
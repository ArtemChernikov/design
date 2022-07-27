package ru.job4j.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        Assertions.assertTrue(set.add(1));
        Assertions.assertTrue(set.contains(1));
        Assertions.assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        Assertions.assertTrue(set.add(null));
        Assertions.assertTrue(set.contains(null));
        Assertions.assertFalse(set.add(null));
    }

    @Test
    public void whenAddNullAndAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        Assertions.assertTrue(set.add(null));
        Assertions.assertTrue(set.contains(null));
        Assertions.assertTrue(set.add(1));
        Assertions.assertFalse(set.contains(2));
        Assertions.assertTrue(set.add(2));
        Assertions.assertFalse(set.add(null));
    }
}
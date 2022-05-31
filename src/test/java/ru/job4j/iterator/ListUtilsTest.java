package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterLastWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 10, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfter1Index() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 1, 3);
        assertThat(input, is(Arrays.asList(0, 1, 3, 2)));
    }

    @Test
    public void whenRemoveIfNegativeNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, -3, 4, -5, 6, 7, 8, -9));
        ListUtils.removeIf(input, x -> x < 0);
        assertThat(input, is(Arrays.asList(0, 1, 2, 4, 6, 7, 8)));
    }

    @Test
    public void whenReplacePositiveNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, -3, 4, -5, 6, 7, 8, -9));
        ListUtils.replaceIf(input, x -> x >= 0, -1);
        assertThat(input, is(Arrays.asList(-1, -1, -1, -3, -1, -5, -1, -1, -1, -9)));
    }

    @Test
    public void whenRemoveOneDuplicateNumbers() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2));
        List<Integer> list2 = new ArrayList<>(List.of(1));
        ListUtils.removeAll(list1, list2);
        assertThat(list1, is(List.of(2)));
    }

    @Test
    public void whenRemoveThreeDuplicateNumbers() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 8, 10, 2, 3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(list1, list2);
        assertThat(list1, is(Arrays.asList(8, 10)));
    }
}
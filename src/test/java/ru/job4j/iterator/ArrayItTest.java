package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class ArrayItTest {

    @Test
    public void whenNextFromEmpty() {
        ArrayIt it = new ArrayIt(
                new int[]{}
        );
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void whenMultiCallHasNextThenTrue() {
        ArrayIt it = new ArrayIt(
                new int[]{1, 2, 3}
        );
        boolean rsl = it.hasNext();
        assertThat(rsl).isTrue();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
    }
}
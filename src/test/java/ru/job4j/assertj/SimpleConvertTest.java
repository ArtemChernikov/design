package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).isNotNull()
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "zero", "nine");
        assertThat(list).isNotNull()
                .hasSize(3)
                .contains("first")
                .contains("zero", Index.atIndex(1))
                .containsAnyOf("zero")
                .doesNotContain("nine", Index.atIndex(0));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "zero", "nine", "first");
        assertThat(set).isNotNull()
                .hasSize(3)
                .doesNotContain("ten")
                .containsOnly("zero", "nine", "first");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> set = simpleConvert.toMap("first", "zero", "nine", "first");
        assertThat(set).isNotNull()
                .hasSize(3)
                .containsKeys("first", "zero", "nine")
                .containsValues(0, 1, 2)
                .containsEntry("first", 0);
    }
}
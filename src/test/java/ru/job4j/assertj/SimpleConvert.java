package ru.job4j.assertj;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SimpleConvert {
    public String[] toArray(String... example) {
        return example;
    }

    public List<String> toList(String... example) {
        return Arrays.stream(example).toList();
    }

    public Set<String> toSet(String... example) {
        return Arrays.stream(example).collect(Collectors.toSet());
    }

    public Map<String, Integer> toMap(String... example) {
        AtomicInteger i = new AtomicInteger();
        return Arrays.stream(example)
                .collect(Collectors.toMap(
                        e -> e,
                        e -> i.getAndIncrement(),
                        (first, second) -> first)
                );
    }
}

package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Artem"));
    }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "./data/Pair_With_Comment_And_Empty_Lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Artem"));
        assertThat(config.value("surname"), is("Chernikov"));
    }

    @Test
    public void whenPairWithDoubleEquals() {
        String path = "./data/PairWithDoubleEquals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Chernikov=hh"));
    }

    @Test
    public void whenKeyWithoutValue() {
        String path = "./data/KeyWithoutValue.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }
}
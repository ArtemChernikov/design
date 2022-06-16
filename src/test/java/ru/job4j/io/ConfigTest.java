package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyWithoutValue() {
        String path = "./data/KeyWithoutValue.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Chernikov=hh"));
    }
}
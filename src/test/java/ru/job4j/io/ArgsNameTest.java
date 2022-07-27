package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertThat(jvm.get("request"), is("?msg=Exit="));
    }

    @Test
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        assertThrows(IllegalArgumentException.class, () -> jvm.get("Xms"));
    }

    @Test
    public void whenWrongSomeArgument() {
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[]{"-enconding=UTF-8", "-Xmx="}));
    }

    @Test
    public void whenEmptyArguments() {
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[]{}));
    }

    @Test
    public void whenMissingFirstCharacter() {
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[]{"enconding=UTF-8", "-Xmx=512"}));
    }

    @Test
    public void whenKeyIsMissing() {
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[]{"-=UTF-8", "-Xmx=512"}));
    }

    @Test
    public void whenEqualsIsMissing() {
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[]{"-encondingUTF-8", "-Xmx=512"}));
    }

    @Test
    public void whenKeyAndValueIsMissing() {
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[]{"-=", "-Xmx=512"}));
    }
}
package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        jvm.get("Xms");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName jvm = ArgsName.of(new String[]{"-enconding=UTF-8", "-Xmx="});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyArguments() {
        ArgsName jvm = ArgsName.of(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMissingFirstCharacter() {
        ArgsName jvm = ArgsName.of(new String[]{"enconding=UTF-8", "-Xmx=512"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsMissing() {
        ArgsName jvm = ArgsName.of(new String[]{"-=UTF-8", "-Xmx=512"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEqualsIsMissing() {
        ArgsName jvm = ArgsName.of(new String[]{"-encondingUTF-8", "-Xmx=512"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyAndValueIsMissing() {
        ArgsName jvm = ArgsName.of(new String[]{"-=", "-Xmx=512"});
    }
}
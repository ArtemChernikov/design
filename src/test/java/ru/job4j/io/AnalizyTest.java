package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {

    private final Analizy analizy = new Analizy();

    @TempDir
    Path directory = Paths.get("./TempDir");

    @Test
    public void whenServerIsUnavailable() throws IOException {
        File source = new File(directory + "source.txt");
        File target = new File(directory + "target.txt");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("200 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(x -> rsl.append(x).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;" + System.lineSeparator()
                + "11:01:02;11:02:02;" + System.lineSeparator()));
    }

    @Test
    public void whenServerIsAvailable() throws IOException {
        File source = new File(directory + "/source.txt");
        File target = new File(directory + "/target.txt");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("200 10:59:01");
            writer.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(x -> rsl.append(x).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is(""));
    }
}
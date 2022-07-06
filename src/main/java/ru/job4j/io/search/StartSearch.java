package ru.job4j.io.search;

import ru.job4j.io.ArgsName;

public class StartSearch {
    public static void main(String[] args) {
        FileSearch search = new FileSearch();
        ArgsName argsName = search.validate(args);
        search.writeFiles(argsName);
    }
}

package ru.job4j.io.search;

import ru.job4j.io.ArgsName;

/**
 * Класс используется для точки запуска программы программы для поиска файлов
 * Необходимо запуска с входными параметрами запуска, например:
 * "-d=c:/ -n=*.?xt -t=mask -o=log.txt"
 * где d - директория в которой нужно начинать поиск,
 * n - имя файла, маска или регулярное выражение (искомого файла или файлов)
 * t - тип поиска (mask искать по маске, name по полному совпадению имени, regex по регулярному выражению)
 * o - для записи найденных путей к файлам в отдельный файл
 * Используется класс {@link FileSearch}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class StartSearch {
    public static void main(String[] args) {
        FileSearch search = new FileSearch();
        ArgsName argsName = search.validate(args);
        search.writeFiles(argsName);
    }
}

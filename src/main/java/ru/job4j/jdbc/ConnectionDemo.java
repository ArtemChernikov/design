package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс демонстрирует подключение к БД с помощью данных (driver, url, login, password),
 * которые лежат в корне проекта в файле app.properties, извлечение данных из файла
 * производим с помощью класса {@link Config}, который изымает данные по ключу
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("app.properties");
        config.load();
        Class.forName(config.value("driver"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}

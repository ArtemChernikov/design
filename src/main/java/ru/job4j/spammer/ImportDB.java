package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс описывает модель для добавления данных в таблицу из файла,
 * со свойствами <b>cfg</b>, <b>dump</b>
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ImportDB {
    /**
     * Поле с объектом {@link Properties}
     */
    private final Properties cfg;
    /**
     * Поле путь к файлу для загрузки данных
     */
    private final String dump;

    /**
     * Конструктор - создание объекта с определенными значениями
     *
     * @param cfg  - объект {@link Properties}
     * @param dump - путь к файлу
     */
    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод используется для загрузки данных из файла,
     * преобразования их в объекты {@link User}
     * и добавления в коллекцию
     *
     * @return - возвращает список с объектами {@link User}
     * @throws IOException - может выбросить {@link IOException}
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            String line;
            while ((line = rd.readLine()) != null) {
                String[] array = line.split(";");
                if (array.length != 2 || array[0].isEmpty() && array[1].isEmpty()) {
                    throw new IllegalArgumentException("Проверьте корректность данных в файле!");
                }
                users.add(new User(array[0], array[1]));
            }
        }
        return users;
    }

    /**
     * Метод используется для сохранения списка с объектами {@link User} в БД
     *
     * @param users - список с объектами {@link User}
     * @throws ClassNotFoundException - может выбросить {@link ClassNotFoundException}
     * @throws SQLException           - может выбросить {@link SQLException}
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    /**
     * Вложенный класс используется для хранения данных из файла
     */
    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}

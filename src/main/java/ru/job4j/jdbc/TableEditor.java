package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.StringJoiner;
import java.util.Properties;

/**
 * Класс описывает модель для изменения таблицы в БД со свойствами <b>connection</b>, <b>properties</b>
 * Класс реализует интерфейс {@link AutoCloseable}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class TableEditor implements AutoCloseable {
    /**
     * Поле подключение к БД
     */
    private Connection connection;
    /**
     * Поле {@link Properties} для изъятия данных для настройки подключения к БД
     */
    private final Properties properties;

    /**
     * Конструктор - создание объекта с определенными значениями
     * Вызывает метод {@link TableEditor#initConnection()}
     *
     * @param properties - поле {@link Properties}
     * @throws Exception - может выбросить {@link Exception}
     */
    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    /**
     * Метод используется для получения поля {@link TableEditor#connection}
     *
     * @return - возвращает {@link Connection}
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Метод используется для подключения к БД
     *
     * @throws Exception - может выбросить {@link Exception}
     */
    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"), properties.getProperty("password"));
    }

    /**
     * Метод используется для выполнения запроса в БД
     *
     * @param sql - строка с запросом
     */
    private void run(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Метод создает запрос для создания таблицы без столбцов
     * Используется метод {@link TableEditor#run(String)} для выполнения запроса
     *
     * @param tableName - имя таблицы
     */
    public void createTable(String tableName) {
        run(String.format("CREATE TABLE IF NOT EXISTS %s("
                + "id serial PRIMARY KEY"
                + ");", tableName));
    }

    /**
     * Метод создает запрос для удаления таблицы
     * Используется метод {@link TableEditor#run(String)} для выполнения запроса
     *
     * @param tableName - имя таблицы
     */
    public void dropTable(String tableName) {
        run(String.format("DROP TABLE %s;", tableName));
    }

    /**
     * Метод создает запрос для добавления столбца в таблицу
     * Используется метод {@link TableEditor#run(String)} для выполнения запроса
     *
     * @param tableName  - имя таблицы
     * @param columnName - имя столбца
     * @param type       - тип данных в столбце
     */
    public void addColumn(String tableName, String columnName, String type) {
        run(String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type));
    }

    /**
     * Метод создает запрос для удаления столбца в таблице
     * Используется метод {@link TableEditor#run(String)} для выполнения запроса
     *
     * @param tableName  - имя таблицы
     * @param columnName - имя столбца
     */
    public void dropColumn(String tableName, String columnName) {
        run(String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName));
    }

    /**
     * Метод создает запрос для изменения названия столбца таблицы
     *
     * @param tableName     - имя таблицы
     * @param columnName    - имя столбца
     * @param newColumnName - новое имя столбца
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        run(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName));
    }

    /**
     * Метод используется для вывода данных из таблицы в консоль
     *
     * @param connection - {@link Connection}
     * @param tableName  - название таблицы
     * @return - возвращает таблицу в виде строки
     * @throws Exception - может выбросить {@link Exception}
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    /**
     * Метод используется для закрытия подключения к таблице
     *
     * @throws Exception - может выбросить {@link Exception}
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                Connection connection = tableEditor.getConnection();
                tableEditor.createTable("cars");
                System.out.println(getTableScheme(connection, "cars"));
                tableEditor.addColumn("cars", "engine", "varchar(50)");
                System.out.println(getTableScheme(connection, "cars"));
                tableEditor.renameColumn("cars", "engine", "car_body");
                System.out.println(getTableScheme(connection, "cars"));
                tableEditor.dropColumn("cars", "car_body");
                System.out.println(getTableScheme(connection, "cars"));
                tableEditor.dropTable("cars");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

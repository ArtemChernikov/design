package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс демонстрирует работу сервера под видом бота
 * В классе используется логгирование {@link org.slf4j.Logger},
 * для вывода информации, если будет выброшен {@link IOException}
 * Клиент отправляет запросы серверу, а сервер на них отвечает
 * В качестве клиента используется программа cURL
 * Пример запроса: curl -i "http://localhost:9000/?msg=Hello"
 * На сообщение "Hello", сервер отвечает "Hello"
 * На сообщение "Exit", сервер заканчивает свою работу
 * На любое другое сообщение ответ будет "What"
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class EchoServer {
    /**
     * Поле лог
     */
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("msg=")) {
                            switch (str) {
                                case "GET /?msg=Hello HTTP/1.1" -> out.write("Hello\r\n\r\n".getBytes());
                                case "GET /?msg=Exit HTTP/1.1" -> server.close();
                                default -> out.write("What\r\n\r\n".getBytes());
                            }
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}



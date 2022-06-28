package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Класс демонстрирует работу Log4j
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}

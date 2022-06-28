package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Класс демонстрирует работу Log4j через slf4j
 *
 * @author ARTEM CHERNIKOV
 * @version 1.1
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 125;
        short s = 32589;
        int i = 242654545;
        long l = 858524585145845L;
        float f = 1.2F;
        double d = 23.42;
        boolean bool = true;
        char c = 'c';
        LOG.debug("b = {}, s = {}, i = {}, l = {}, f = {}, d = {}, bool = {}, c = {}",
                b, s, i, l, f, d, bool, c);
    }
}

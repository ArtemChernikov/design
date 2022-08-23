package ru.job4j.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
class CinemaTest {
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Test()
    public void whenInvalidRow() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, -1, 1, date));
    }

    @Test()
    public void whenInvalidColumn() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 3, -4, date));
    }

    @Test()
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar(1998, Calendar.FEBRUARY, 5);
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 3, 2, date));
    }

    @Test()
    public void whenTicketBought() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account, 3, 2, date);
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 3, 2, date));
    }
}
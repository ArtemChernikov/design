package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJSONTest {
    @Test
    public void whenStandardReportJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        LocalDateTime hired = LocalDateTime.ofInstant(worker.getHired().toInstant(), worker.getHired()
                .getTimeZone().toZoneId());
        LocalDateTime fired = LocalDateTime.ofInstant(worker.getFired().toInstant(), worker.getFired()
                .getTimeZone().toZoneId());
        Report reportJSON = new ReportJSON(store);
        String expect = "[{\"name\":\"Ivan\",\"hired\":"
                + "{\"year\":" + hired.getYear()
                + ",\"month\":" + (hired.getMonth().getValue() - 1)
                + ",\"dayOfMonth\":" + hired.getDayOfMonth()
                + ",\"hourOfDay\":" + hired.getHour()
                + ",\"minute\":" + hired.getMinute()
                + ",\"second\":" + hired.getSecond()
                + "},\"fired\""
                + ":{\"year\":" + fired.getYear()
                + ",\"month\":" + (fired.getMonth().getValue() - 1)
                + ",\"dayOfMonth\":" + fired.getDayOfMonth()
                + ",\"hourOfDay\":" + fired.getHour()
                + ",\"minute\":" + fired.getMinute()
                + ",\"second\":" + fired.getSecond()
                + "},\"salary\":100.0}]";
        assertThat(reportJSON.generate(em -> true)).isEqualTo(expect);
    }
}
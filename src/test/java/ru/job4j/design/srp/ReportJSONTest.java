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
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"Ivan\",\"hired\":{\"year\":")
                .append(hired.getYear())
                .append(",\"month\":")
                .append(hired.getMonth().getValue() - 1)
                .append(",\"dayOfMonth\":")
                .append(hired.getDayOfMonth())
                .append(",\"hourOfDay\":")
                .append(hired.getHour())
                .append(",\"minute\":")
                .append(hired.getMinute())
                .append(",\"second\":")
                .append(hired.getSecond())
                .append("},\"fired\":{\"year\":")
                .append(fired.getYear())
                .append(",\"month\":")
                .append(fired.getMonth().getValue() - 1)
                .append(",\"dayOfMonth\":")
                .append(fired.getDayOfMonth())
                .append(",\"hourOfDay\":")
                .append(fired.getHour())
                .append(",\"minute\":")
                .append(fired.getMinute())
                .append(",\"second\":")
                .append(fired.getSecond())
                .append("},\"salary\":100.0}]");
        assertThat(reportJSON.generate(em -> true)).isEqualTo(expect.toString());
    }
}
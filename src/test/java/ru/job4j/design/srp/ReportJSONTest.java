package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJSONTest {
    @Test
    public void whenStandardReportJSONGenerated() {
        Gson g = new GsonBuilder().create();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report reportJSON = new ReportJSON(store);
        StringBuilder expect = new StringBuilder()
                .append("[")
                .append(g.toJson(worker))
                .append("]");
        assertThat(reportJSON.generate(em -> true)).isEqualTo(expect.toString());
    }
}
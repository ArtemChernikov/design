package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportXMLTest {
    @Test
    public void whenStandardReportJSONGenerated() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.SSSXXX");
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report reportXML = new ReportXML(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n"
                + "<employee>\n"
                + "    <employees>\n"
                + "        <fired>" + dateFormat.format(worker.getHired().getTime()) + "</fired>\n"
                + "        <hired>" + dateFormat.format(worker.getHired().getTime()) + "</hired>\n"
                + "        <name>Ivan</name>\n"
                + "        <salary>100.0</salary>\n"
                + "    </employees>\n"
                + "</employee>\n";
        assertThat(reportXML.generate(em -> true)).isEqualTo(expect);
    }

}
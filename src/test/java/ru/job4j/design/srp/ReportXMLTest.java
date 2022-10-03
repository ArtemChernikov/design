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
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employee>
                    <employees>
                        <fired>""" + dateFormat.format(worker.getHired().getTime())
                + """
                </fired>
                        <hired>""" + dateFormat.format(worker.getHired().getTime())
                + """
                </hired>
                        <name>Ivan</name>
                        <salary>100.0</salary>
                    </employees>
                </employee>
                """;
        assertThat(reportXML.generate(em -> true)).isEqualTo(expect);
    }

}
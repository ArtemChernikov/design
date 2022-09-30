package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

/**
 * Класс используется для создания отчетов в XML формате.
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class ReportXML implements Report {
    private final Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(EmployeeXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            EmployeeXML employeeXML = new EmployeeXML(store.findBy(filter));
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(employeeXML, writer);
                xml = writer.getBuffer().toString();
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(xml);
        return xml;
    }
}

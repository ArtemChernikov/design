package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Класс используется для сериализации и десериализации объекта
 * в формат XML и из него, с помощью JAXB
 * Используется класс {@link Cafe}
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        try {
            Cafe cafe = new Cafe("LuxuryCafe", new Address("Moscow", "Ubileynaya", 30, 4),
                    true, 30, new String[]{"Borsh", "ChikenSoup", "CheeseCake", "Sandwich"});
            JAXBContext context = JAXBContext.newInstance(Cafe.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xml = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(cafe, writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(xml)) {
                Cafe result = (Cafe) unmarshaller.unmarshal(reader);
                System.out.println(result);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}

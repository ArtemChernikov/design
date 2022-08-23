package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

@Disabled
class TemplateAppTest {

    @Test
    void whenManyKeyInMap() {
        TemplateApp templateApp = new TemplateApp();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Artem", "subject", "you", "surname", "Chernikov");
        assertThrows(IllegalArgumentException.class, () -> templateApp.produce(template, map));
    }

    @Test
    void whenKeyNotExistInMap() {
        TemplateApp templateApp = new TemplateApp();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("subject", "you", "surname", "Chernikov");
        assertThrows(IllegalArgumentException.class, () -> templateApp.produce(template, map));
    }

    @Test
    void whenTemplateIsIncorrect() {
        TemplateApp templateApp = new TemplateApp();
        String template = "I am a ____, Who are ____? ";
        Map<String, String> map = Map.of("name", "Artem", "subject", "you");
        assertThrows(IllegalArgumentException.class, () -> templateApp.produce(template, map));
    }

    @Test
    void whenValueInMapEqualsNull() {
        TemplateApp templateApp = new TemplateApp();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Artem");
        map.put("subject", null);
        assertThrows(IllegalArgumentException.class, () -> templateApp.produce(template, map));
    }

    @Test
    void whenProduce() {
        TemplateApp templateApp = new TemplateApp();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Artem");
        map.put("subject", "you");
        String expected = templateApp.produce(template, map);
        assertThat(expected).isEqualTo("I am a Artem, Who are you ");
    }

}
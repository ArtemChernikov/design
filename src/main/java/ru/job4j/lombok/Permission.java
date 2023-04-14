package ru.job4j.lombok;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.04.2023
 */
@Builder(builderMethodName = "of")
@ToString
public class Permission {
    private int id;
    private String name;
    @Singular("rules")
    private List<String> rules;
}

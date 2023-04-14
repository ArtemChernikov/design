package ru.job4j.lombok;

import lombok.*;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.04.2023
 */
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @NonNull
    @Getter
    @EqualsAndHashCode.Include
    private int id;
    @Getter
    @Setter
    private String name;
}

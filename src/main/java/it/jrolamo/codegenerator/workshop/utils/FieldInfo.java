package it.jrolamo.codegenerator.workshop.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Vittorio Valent
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
public class FieldInfo {

    private String name;

    private String type;

}

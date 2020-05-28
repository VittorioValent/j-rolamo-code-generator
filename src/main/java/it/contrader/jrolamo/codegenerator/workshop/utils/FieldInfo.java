package it.contrader.jrolamo.codegenerator.workshop.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Vittorio Valent
 * @since 0.0.1
 */
@Data
@Builder
@Slf4j
@AllArgsConstructor
public class FieldInfo {

    private String name;

    private String type;

}

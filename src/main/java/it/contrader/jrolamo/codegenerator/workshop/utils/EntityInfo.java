package it.contrader.jrolamo.codegenerator.workshop.utils;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Vittorio Valent
 *
 */
@Data
@Builder
@AllArgsConstructor
public class EntityInfo {

    private String entityName;

    private List<FieldInfo> fields;

    private Boolean auditable;

    private ServiceTypeEnum serviceType;

    private ControllerTypeEnum controllerType;

    public String getEntityName() {
        return GeneratorUtils.capitalize(this.entityName);
    }
}

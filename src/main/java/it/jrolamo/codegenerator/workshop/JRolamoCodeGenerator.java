package it.jrolamo.codegenerator.workshop;

import it.jrolamo.codegenerator.workshop.utils.EntityInfo;
import it.jrolamo.codegenerator.workshop.utils.ServiceTypeEnum;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Vittorio Valent
 * @since 0.0.1
 *
 */
@Slf4j
public class JRolamoCodeGenerator {

    /**
     *
     * @param entityInfo
     */
    public static void generateEntityFlow(EntityInfo entityInfo) {

        try {
            Boolean auditable = entityInfo.getAuditable();
            if (!entityInfo.getServiceType().equals(ServiceTypeEnum.PUBLIC)) {
                auditable = true;
            }
            EntityGenerator.generateEntityClass(entityInfo.getEntityName(), entityInfo.getFields(), auditable);
            DTOGenerator.generateDTOClass(entityInfo.getEntityName(), entityInfo.getFields(), auditable);
            SpecificationGenerator.generateSpecificationClass(entityInfo.getEntityName(), entityInfo.getFields());
            RepositoryGenerator.generateRepositoryInterface(entityInfo.getEntityName(), entityInfo.getServiceType());
            MapperGenerator.generateMapperInterface(entityInfo.getEntityName());
            ServiceGenerator.generateServiceClass(entityInfo.getEntityName(), entityInfo.getServiceType());
            ControllerGenerator.generateControllerClass(entityInfo.getEntityName(), entityInfo.getControllerType(),
                    entityInfo.getServiceType());
        } catch (IOException e) {
            log.error("Impossible to generate required code", e);
        }
    }
}

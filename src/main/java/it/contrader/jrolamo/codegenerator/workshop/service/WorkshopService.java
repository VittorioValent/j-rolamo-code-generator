package it.contrader.jrolamo.codegenerator.workshop.service;

import it.contrader.jrolamo.codegenerator.workshop.utils.EntityInfo;
import it.contrader.jrolamo.codegenerator.workshop.utils.FieldInfo;
import it.contrader.jrolamo.codegenerator.workshop.utils.GeneratorUtils;
import it.contrader.jrolamo.generics.domain.AbstractModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vittorio Valent
 */
@Service
public class WorkshopService {

    public List<EntityInfo> getAllEntities() throws ClassNotFoundException {
        Reflections entityReflection = new Reflections(GeneratorUtils.DOMAIN_PACKAGE);

        Set<Class<? extends Object>> entityClasses = entityReflection.getSubTypesOf((Class) AbstractModel.class);

        List<EntityInfo> entityInfoList = new ArrayList<>();
        for (Class entityClass : entityClasses) {
            if (!entityClass.getSimpleName().equals("AuditModel")) {
                entityInfoList.add(getEntityInfo(entityClass));
            }
        }
        return entityInfoList;
    }

    private EntityInfo getEntityInfo(Class entityClass) {
        String entityName = entityClass.getSimpleName();
        Boolean auditable = entityClass.getSuperclass().getSimpleName().equals("AuditModel");

        return EntityInfo.builder()
                .auditable(auditable)
                .entityName(entityName)
                .fields(getDeclaredFields(entityClass))
                .build();
    }

    private List<FieldInfo> getDeclaredFields(Class entityClass) {
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        List<Field> declaredFields = Arrays.asList(entityClass.getDeclaredFields());
        for (Field field : declaredFields) {
            FieldInfo fieldInfo = FieldInfo.builder()
                    .name(field.getName())
                    .type(field.getType().getTypeName())
                    .build();
            fieldInfoList.add(fieldInfo);
        }
        return fieldInfoList;
    }
}

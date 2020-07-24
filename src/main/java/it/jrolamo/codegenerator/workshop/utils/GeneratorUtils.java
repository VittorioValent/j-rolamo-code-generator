package it.jrolamo.codegenerator.workshop.utils;

import com.squareup.javapoet.JavaFile;
import it.jrolamo.generics.controller.PrivateCrudController;
import it.jrolamo.generics.controller.PrivateReadController;
import it.jrolamo.generics.controller.ProtectedCrudController;
import it.jrolamo.generics.controller.PublicCrudController;
import it.jrolamo.generics.controller.PublicReadController;
import it.jrolamo.generics.domain.AbstractDTO;
import it.jrolamo.generics.domain.AbstractModel;
import it.jrolamo.generics.domain.AuditDTO;
import it.jrolamo.generics.domain.AuditModel;
import it.jrolamo.generics.domain.EntitySpecification;
import it.jrolamo.generics.mapper.IMapper;
import it.jrolamo.generics.repositoy.IPrivateRepository;
import it.jrolamo.generics.repositoy.IRepository;
import it.jrolamo.generics.service.PrivateService;
import it.jrolamo.generics.service.ProtectedService;
import it.jrolamo.generics.service.PublicService;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Vittorio Valent
 * @since 0.0.1
 *
 */
@Slf4j
public class GeneratorUtils {

    private static final String PROJECT_PATH = "src/main/java";

    public static final String DEFAULT_INDENTATION = "    ";

    public static String BASE_PACKAGE;

    public static String DOMAIN_PACKAGE;

    public static String DTO_PACKAGE;

    public static String FILTER_PACKAGE;

    public static String REPOSITORY_PACKAGE;

    public static String SERVICE_PACKAGE;

    public static String MAPPER_PACKAGE;

    public static String CONTROLLER_PACKAGE;

    /**
     *
     * @return EntitySpecification Class.
     */
    public static Class getFilterSuperClass() {
        return EntitySpecification.class;
    }

    /**
     *
     * @param auditable
     * @return AuditModel if auditable is true, else AbstractModel.
     */
    public static Class getEntitySuperClass(Boolean auditable) {
        if (auditable) {
            return AuditModel.class;
        } else {
            return AbstractModel.class;
        }
    }

    /**
     *
     * @param serviceTypeEnum
     * @return IPrivateRepository if serviceTypeEnum is PRIVATE, else IRepository.
     */
    public static Class getRepositorySuperInterface(ServiceTypeEnum serviceTypeEnum) {
        if (serviceTypeEnum.equals(ServiceTypeEnum.PRIVATE)) {
            return IPrivateRepository.class;
        } else {
            return IRepository.class;
        }
    }

    /**
     *
     * @param auditable
     * @return AuditDTO if auditable is true, else AbstractDTO.
     */
    public static Class getDTOSuperClass(Boolean auditable) {
        if (auditable) {
            return AuditDTO.class;
        } else {
            return AbstractDTO.class;
        }
    }

    /**
     *
     * @return IMapper interface.
     */
    public static Class getMapperSuperInterface() {
        return IMapper.class;
    }

    /**
     *
     * @param serviceType
     * @return PublicService if serviceType is PUBLIC, ProtectedService if
     *         serviceType is PROTECTED, PrivateService if serviceType is PRIVATE.
     */
    public static Class getServiceSuperClass(ServiceTypeEnum serviceType) {
        switch (serviceType) {
            case PUBLIC:
                return PublicService.class;
            case PROTECTED:
                return ProtectedService.class;
            case PRIVATE:
                return PrivateService.class;
        }
        return null;
    }

    /**
     * This method returns the correct Controller superclass checking both
     * controllerType and serviceType.
     *
     * @param controllerType
     * @param serviceType
     * @return Controller superclass
     */
    public static Class getControllerSuperClass(ControllerTypeEnum controllerType, ServiceTypeEnum serviceType) {
        switch (controllerType) {
            case READ:
                switch (serviceType) {
                    case PUBLIC:
                    case PROTECTED:
                        return PublicReadController.class;
                    case PRIVATE:
                        return PrivateReadController.class;
                }
            case CRUD:
                switch (serviceType) {
                    case PUBLIC:
                        return PublicCrudController.class;
                    case PROTECTED:
                        return ProtectedCrudController.class;
                    case PRIVATE:
                        return PrivateCrudController.class;
                }
        }
        return null;
    }

    public static Class getFieldType(String type, ModelType modelType) {
        switch (type.trim().toLowerCase()) {
            case "string":
                return String.class;
            case "integer":
            case "int":
                return Integer.class;
            case "long":
                return Long.class;
            case "boolean":
                return Boolean.class;
            case "float":
                return Float.class;
            case "double":
                return Double.class;
            case "date":
                return Date.class;
            case "bigdecimal":
                return BigDecimal.class;
            default:
                break;
        }

        Class<?> clazz = String.class;
        String location = modelType.equals(ModelType.ENTITY) ? DOMAIN_PACKAGE : DTO_PACKAGE;
        String suffix = modelType.equals(ModelType.DTO) ? "DTO" : "";
        try {
            clazz = Class.forName(location + "." + type + suffix);
        } catch (ClassNotFoundException e) {
            log.error("Impossible to retrieve class from " + type + suffix + ", setting java.lang.String as type", e);
        }
        return clazz;
    }

    public static Boolean getMappedEntity(String type, ModelType modelType) {
        String location = modelType.equals(ModelType.ENTITY) ? DOMAIN_PACKAGE : DTO_PACKAGE;
        String suffix = modelType.equals(ModelType.DTO) ? "DTO" : "";
        try {
            Class.forName(location + "." + type + suffix);
            return Boolean.TRUE;
        } catch (ClassNotFoundException e) {
            return Boolean.FALSE;
        }
    }

    public static void save(JavaFile javaFile) throws IOException {
        File file = new File(PROJECT_PATH);
        javaFile.writeTo(file);
    }

    public static String capitalize(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

}

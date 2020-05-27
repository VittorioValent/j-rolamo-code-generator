package it.contrader.jrolamo.codegenerator.workshop.utils;

import com.squareup.javapoet.JavaFile;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Vittorio Valent
 * @since 1.0
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

    public static String DOMAIN_GENERIC_PACKAGE;

    public static String REPOSITORY_PACKAGE;

    public static String REPOSITORY_GENERIC_PACKAGE;

    public static String SERVICE_PACKAGE;

    public static String SERVICE_GENERIC_PACKAGE;

    public static String MAPPER_PACKAGE;

    public static String MAPPER_GENERIC_PACKAGE;

    public static String CONTROLLER_PACKAGE;

    public static String CONTROLLER_GENERIC_PACKAGE;

    /**
     *
     * @return EntitySpecification Class.
     */
    public static Class getFilterSuperClass() {
        return getClass(DOMAIN_GENERIC_PACKAGE, "EntitySpecification");
    }

    /**
     *
     * @param auditable
     * @return AuditModel if auditable is true, else AbstractModel.
     */
    public static Class getEntitySuperClass(Boolean auditable) {
        if (auditable) {
            return getClass(DOMAIN_GENERIC_PACKAGE, "AuditModel");
        } else {
            return getClass(DOMAIN_GENERIC_PACKAGE, "AbstractModel");
        }
    }

    /**
     *
     * @param serviceTypeEnum
     * @return IPrivateRepository if serviceTypeEnum is PRIVATE, else
     * IRepository.
     */
    public static Class getRepositorySuperInterface(ServiceTypeEnum serviceTypeEnum) {
        if (serviceTypeEnum.equals(ServiceTypeEnum.PRIVATE)) {
            return getClass(REPOSITORY_GENERIC_PACKAGE, "IPrivateRepository");
        } else {
            return getClass(REPOSITORY_GENERIC_PACKAGE, "IRepository");
        }
    }

    /**
     *
     * @param auditable
     * @return AuditDTO if auditable is true, else AbstractDTO.
     */
    public static Class getDTOSuperClass(Boolean auditable) {
        if (auditable) {
            return getClass(DOMAIN_GENERIC_PACKAGE, "AuditDTO");
        } else {
            return getClass(DOMAIN_GENERIC_PACKAGE, "AbstractDTO");
        }
    }

    /**
     *
     * @return IMapper interface.
     */
    public static Class getMapperSuperInterface() {
        return getClass(MAPPER_GENERIC_PACKAGE, "IMapper");
    }

    /**
     *
     * @param serviceType
     * @return PublicService if serviceType is PUBLIC, ProtectedService if
     * serviceType is PROTECTED, PrivateService if serviceType is PRIVATE.
     */
    public static Class getServiceSuperClass(ServiceTypeEnum serviceType) {
        switch (serviceType) {
            case PUBLIC:
                return getClass(SERVICE_GENERIC_PACKAGE, "PublicService");
            case PROTECTED:
                return getClass(SERVICE_GENERIC_PACKAGE, "ProtectedService");
            case PRIVATE:
                return getClass(SERVICE_GENERIC_PACKAGE, "PrivateService");
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
                        return getClass(CONTROLLER_GENERIC_PACKAGE, "PublicReadController");
                    case PRIVATE:
                        return getClass(CONTROLLER_GENERIC_PACKAGE, "PrivateReadController");
                }
            case CRUD:
                switch (serviceType) {
                    case PUBLIC:
                        return getClass(CONTROLLER_GENERIC_PACKAGE, "PublicCrudController");
                    case PROTECTED:
                        return getClass(CONTROLLER_GENERIC_PACKAGE, "ProtectedCrudController");
                    case PRIVATE:
                        return getClass(CONTROLLER_GENERIC_PACKAGE, "PrivateCrudController");
                }
        }
        return null;
    }

    private static Class getClass(String packageName, String className) {
        try {
            return Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException", e);
            return null;
        }
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
            log.error("Impossible to retrieve class from " + type + ", setting java.lang.String as type", e);
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

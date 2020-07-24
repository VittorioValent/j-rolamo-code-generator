package it.jrolamo.codegenerator.workshop.utils;

/**
 *
 * @author Vittorio Andreoni
 * @since 0.0.1
 */
public enum ControllerTypeEnum {

    READ("READ"), CRUD("CRUD");

    private final String controllerType;

    ControllerTypeEnum(String controllerType) {
        this.controllerType = controllerType;
    }

    public String getControllerType() {
        return controllerType;
    }

}

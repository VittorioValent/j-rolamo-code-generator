package it.contrader.jrolamo.codegenerator.workshop.utils;

/**
 *
 * @author Vittorio Andreoni
 * @since 1.0
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

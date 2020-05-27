package it.contrader.jrolamo.codegenerator.workshop.utils;

/**
 *
 * @author Vittorio Andreoni
 * @since 1.0
 */
public enum ServiceTypeEnum {

    PUBLIC("PUBLIC"), PROTECTED("PROTECTED"), PRIVATE("PRIVATE");

    private String serviceType;

    ServiceTypeEnum(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }
}

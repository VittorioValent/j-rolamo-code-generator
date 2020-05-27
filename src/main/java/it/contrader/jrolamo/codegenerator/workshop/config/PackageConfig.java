package it.contrader.jrolamo.codegenerator.workshop.config;

import it.contrader.jrolamo.codegenerator.workshop.utils.GeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

/**
 *
 * @author Vittorio Valent
 */
@Slf4j
@Configuration
@PropertySource("classpath:application.yml")
public class PackageConfig {

    @Order(1)
    @Value(value = "${jrolamo.codegenerator.package.basepackage:it.project}")
    public void initBasePackageValue(String basePackage) {
        GeneratorUtils.BASE_PACKAGE = basePackage;
        log.info("Setting " + GeneratorUtils.BASE_PACKAGE + " as base package");
    }

    @Value(value = "${jrolamo.codegenerator.package.domain:domain}")
    public void initDomainPackageValue(String domainPackage) {
        GeneratorUtils.DOMAIN_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + domainPackage;
        log.info("Setting " + GeneratorUtils.DOMAIN_PACKAGE + " as domain package");
    }

    @Value(value = "${jrolamo.codegenerator.package.domain-generic:domain.generic}")
    public void initGenericDomainPackageValue(String genericDomainPackage) {
        GeneratorUtils.DOMAIN_GENERIC_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + genericDomainPackage;
        log.info("Setting " + GeneratorUtils.DOMAIN_GENERIC_PACKAGE + " as domain generic package");
    }

    @Value(value = "${jrolamo.codegenerator.package.dto:dto}")
    public void initDtoPackageValue(String dtoPackage) {
        GeneratorUtils.DTO_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + dtoPackage;
        log.info("Setting " + GeneratorUtils.DTO_PACKAGE + " as dto package");
    }

    @Value(value = "${jrolamo.codegenerator.package.filter:filter}")
    public void initFilterPackageValue(String filterPackage) {
        GeneratorUtils.FILTER_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + filterPackage;
        log.info("Setting " + GeneratorUtils.FILTER_PACKAGE + " as filter package");
    }

    @Value(value = "${jrolamo.codegenerator.package.repository:repository}")
    public void initRepositoryPackageValue(String repositoryPackage) {
        GeneratorUtils.REPOSITORY_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + repositoryPackage;
        log.info("Setting " + GeneratorUtils.REPOSITORY_PACKAGE + " as repository package");
    }

    @Value(value = "${jrolamo.codegenerator.package.repository-generic:repository.generic}")
    public void initGenericRepositoryPackageValue(String genericRepositoryPackage) {
        GeneratorUtils.REPOSITORY_GENERIC_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + genericRepositoryPackage;
        log.info("Setting " + GeneratorUtils.REPOSITORY_GENERIC_PACKAGE + " as repository generic package");
    }

    @Value(value = "${jrolamo.codegenerator.package.service:service}")
    public void initServicePackageValue(String servicePackage) {
        GeneratorUtils.SERVICE_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + servicePackage;
        log.info("Setting " + GeneratorUtils.SERVICE_PACKAGE + " as service package");
    }

    @Value(value = "${jrolamo.codegenerator.package.service-generic:service.generic}")
    public void initGenericServicePackageValue(String genericServicePackage) {
        GeneratorUtils.SERVICE_GENERIC_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + genericServicePackage;
        log.info("Setting " + GeneratorUtils.SERVICE_GENERIC_PACKAGE + " as service generic package");
    }

    @Value(value = "${jrolamo.codegenerator.package.mapper:mapper}")
    public void initMapperPackageValue(String mapperPackage) {
        GeneratorUtils.MAPPER_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + mapperPackage;
        log.info("Setting " + GeneratorUtils.MAPPER_PACKAGE + " as mapper package");
    }

    @Value(value = "${jrolamo.codegenerator.package.mapper-generic:mapper.generic}")
    public void initGenericMapperPackageValue(String genericMapperPackage) {
        GeneratorUtils.MAPPER_GENERIC_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + genericMapperPackage;
        log.info("Setting " + GeneratorUtils.MAPPER_GENERIC_PACKAGE + " as mapper generic package");
    }

    @Value(value = "${jrolamo.codegenerator.package.controller:controller}")
    public void initControllerackageValue(String controllerPackage) {
        GeneratorUtils.CONTROLLER_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + controllerPackage;
        log.info("Setting " + GeneratorUtils.CONTROLLER_PACKAGE + " as controller package");
    }

    @Value(value = "${jrolamo.codegenerator.package.controller-generic:controller.generic}")
    public void initGenericControllerackageValue(String genericControllerPackage) {
        GeneratorUtils.CONTROLLER_GENERIC_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + genericControllerPackage;
        log.info("Setting " + GeneratorUtils.CONTROLLER_GENERIC_PACKAGE + " as controller generic package");
    }

}

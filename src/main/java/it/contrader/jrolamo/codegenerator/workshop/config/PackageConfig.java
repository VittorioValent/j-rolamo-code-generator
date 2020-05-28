package it.contrader.jrolamo.codegenerator.workshop.config;

import it.contrader.jrolamo.codegenerator.workshop.JRolamoCodeGenerator;
import it.contrader.jrolamo.codegenerator.workshop.utils.GeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

/**
 *
 * @author Vittorio Valent
 * @since 0.0.1
 */
@Slf4j
@Configuration
@ComponentScan(basePackageClasses = JRolamoCodeGenerator.class)
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

    @Value(value = "${jrolamo.codegenerator.package.service:service}")
    public void initServicePackageValue(String servicePackage) {
        GeneratorUtils.SERVICE_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + servicePackage;
        log.info("Setting " + GeneratorUtils.SERVICE_PACKAGE + " as service package");
    }

    @Value(value = "${jrolamo.codegenerator.package.mapper:mapper}")
    public void initMapperPackageValue(String mapperPackage) {
        GeneratorUtils.MAPPER_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + mapperPackage;
        log.info("Setting " + GeneratorUtils.MAPPER_PACKAGE + " as mapper package");
    }

    @Value(value = "${jrolamo.codegenerator.package.controller:controller}")
    public void initControllerackageValue(String controllerPackage) {
        GeneratorUtils.CONTROLLER_PACKAGE = GeneratorUtils.BASE_PACKAGE + "." + controllerPackage;
        log.info("Setting " + GeneratorUtils.CONTROLLER_PACKAGE + " as controller package");
    }

}

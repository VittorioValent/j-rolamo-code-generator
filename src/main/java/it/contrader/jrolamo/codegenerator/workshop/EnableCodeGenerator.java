package it.contrader.jrolamo.codegenerator.workshop;

import it.contrader.jrolamo.codegenerator.workshop.config.PackageConfig;
import it.contrader.jrolamo.generics.EnableGenerics;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Vittorio Valent
 * @since 0.0.5
 */
@EnableGenerics
@Import(value = {PackageConfig.class})
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EnableCodeGenerator {

}

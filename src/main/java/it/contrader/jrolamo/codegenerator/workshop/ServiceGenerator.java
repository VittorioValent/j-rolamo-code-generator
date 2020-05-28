package it.contrader.jrolamo.codegenerator.workshop;

import java.io.IOException;

import javax.lang.model.element.Modifier;

import org.springframework.stereotype.Service;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import it.contrader.jrolamo.codegenerator.workshop.utils.GeneratorUtils;
import it.contrader.jrolamo.codegenerator.workshop.utils.ServiceTypeEnum;

/**
 * @author Vittorio Valent
 * @since 1.0
 *
 */
public class ServiceGenerator {

    protected static void generateServiceClass(String entityName, ServiceTypeEnum serviceType) throws IOException {
        TypeSpec serviceClass = TypeSpec.classBuilder(entityName + "Service")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Service.class)
                .superclass(ParameterizedTypeName.get(ClassName.get(GeneratorUtils.getServiceSuperClass(serviceType)),
                        ClassName.get(GeneratorUtils.DOMAIN_PACKAGE, entityName),
                        ClassName.get(GeneratorUtils.DTO_PACKAGE, entityName + "DTO")))
                .addJavadoc(CodeBlock
                        .builder()
                        .add("@author JRolamo Code Generator")
                        .build())
                .build();

        JavaFile javaFile = JavaFile.builder(GeneratorUtils.SERVICE_PACKAGE, serviceClass)
                .indent(GeneratorUtils.DEFAULT_INDENTATION)
                .build();

        GeneratorUtils.save(javaFile);
    }
}

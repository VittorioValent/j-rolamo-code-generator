package it.contrader.jrolamo.codegenerator.workshop;

import java.io.IOException;

import javax.lang.model.element.Modifier;

import org.springframework.stereotype.Repository;

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
public class RepositoryGenerator {

    protected static void generateRepositoryInterface(String entityName, ServiceTypeEnum serviceType) throws IOException {
        TypeSpec repositoryInterface = TypeSpec.interfaceBuilder(entityName + "Repository")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Repository.class)
                .addSuperinterface(ParameterizedTypeName.get(ClassName.get(GeneratorUtils.getRepositorySuperInterface(serviceType)),
                        ClassName.get(GeneratorUtils.DOMAIN_PACKAGE, entityName)))
                .addJavadoc(CodeBlock
                        .builder()
                        .add("@author Automatic Code Generator")
                        .build())
                .build();

        JavaFile javaFile = JavaFile.builder(GeneratorUtils.REPOSITORY_PACKAGE, repositoryInterface)
                .indent(GeneratorUtils.DEFAULT_INDENTATION)
                .build();

        GeneratorUtils.save(javaFile);
    }
}

package it.jrolamo.codegenerator.workshop;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import it.jrolamo.codegenerator.workshop.utils.GeneratorUtils;
import java.io.IOException;
import javax.lang.model.element.Modifier;
import org.mapstruct.Mapper;

/**
 * @author Vittorio Valent
 * @since 0.0.1
 *
 */
public class MapperGenerator {

    protected static void generateMapperInterface(String entityName) throws IOException {
        TypeSpec mapperInterface = TypeSpec.interfaceBuilder(entityName + "Mapper").addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(Mapper.class).addMember("componentModel", "$S", "spring").build())
                .addSuperinterface(ParameterizedTypeName.get(ClassName.get(GeneratorUtils.getMapperSuperInterface()),
                        ClassName.get(GeneratorUtils.DOMAIN_PACKAGE, entityName),
                        ClassName.get(GeneratorUtils.DTO_PACKAGE, entityName + "DTO")))
                .addJavadoc(CodeBlock.builder().add("@author JRolamo Code Generator").build()).build();

        JavaFile javaFile = JavaFile.builder(GeneratorUtils.MAPPER_PACKAGE, mapperInterface)
                .indent(GeneratorUtils.DEFAULT_INDENTATION).build();

        GeneratorUtils.save(javaFile);
    }
}

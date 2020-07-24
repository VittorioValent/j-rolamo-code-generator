package it.jrolamo.codegenerator.workshop;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import it.jrolamo.codegenerator.workshop.utils.FieldInfo;
import it.jrolamo.codegenerator.workshop.utils.GeneratorUtils;
import it.jrolamo.codegenerator.workshop.utils.ModelType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Modifier;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Vittorio Valent
 * @since 0.0.1
 *
 */
public class DTOGenerator {

    protected static void generateDTOClass(String entityName, List<FieldInfo> fields, Boolean auditable)
            throws IOException {
        FieldSpec id = FieldSpec.builder(Long.class, "id").addModifiers(Modifier.PRIVATE).build();

        List<FieldSpec> fieldSpecs = new ArrayList<>();

        for (FieldInfo fieldInfo : fields) {
            FieldSpec fieldSpec = FieldSpec
                    .builder(GeneratorUtils.getFieldType(fieldInfo.getType(), ModelType.DTO), fieldInfo.getName())
                    .addModifiers(Modifier.PRIVATE).build();
            fieldSpecs.add(fieldSpec);
        }

        TypeSpec dtoClass = TypeSpec.classBuilder(entityName + "DTO").addModifiers(Modifier.PUBLIC).addField(id)
                .addFields(fieldSpecs).addAnnotation(Data.class)
                .addAnnotation(
                        AnnotationSpec.builder(EqualsAndHashCode.class).addMember("callSuper", "$L", false).build())
                .superclass(GeneratorUtils.getDTOSuperClass(auditable))
                .addJavadoc(CodeBlock.builder().add("@author JRolamo Code Generator").build()).build();

        JavaFile javaFile = JavaFile.builder(GeneratorUtils.DTO_PACKAGE, dtoClass)
                .indent(GeneratorUtils.DEFAULT_INDENTATION).build();

        GeneratorUtils.save(javaFile);
    }
}

package it.contrader.jrolamo.codegenerator.workshop;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import it.contrader.jrolamo.codegenerator.workshop.utils.FieldInfo;
import it.contrader.jrolamo.codegenerator.workshop.utils.GeneratorUtils;
import it.contrader.jrolamo.codegenerator.workshop.utils.ModelType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Modifier;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Vittorio Valent
 *@since 0.0.1
 *
 */
public class EntityGenerator {

    protected static void generateEntityClass(String entityName, List<FieldInfo> fields, Boolean auditable)
            throws IOException {

        FieldSpec id = FieldSpec.builder(Long.class, "id")
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(Id.class)
                .addAnnotation(AnnotationSpec.builder(GeneratedValue.class)
                        .addMember("strategy", "$T.IDENTITY", GenerationType.class)
                        .build())
                .build();

        List<FieldSpec> fieldSpecs = new ArrayList<>();

        for (FieldInfo fieldInfo : fields) {
            FieldSpec fieldSpec = FieldSpec
                    .builder(GeneratorUtils.getFieldType(fieldInfo.getType(), ModelType.ENTITY), fieldInfo.getName())
                    .addModifiers(Modifier.PRIVATE)
                    .build();
            if (GeneratorUtils.getMappedEntity(fieldInfo.getType(), ModelType.ENTITY)) {
                fieldSpec = FieldSpec
                        .builder(GeneratorUtils.getFieldType(fieldInfo.getType(), ModelType.ENTITY), fieldInfo.getName())
                        .addModifiers(Modifier.PRIVATE)
                        .addAnnotation(ManyToOne.class)
                        .addAnnotation(JoinColumn.class)
                        .build();
            }
            fieldSpecs.add(fieldSpec);
        }
        TypeSpec entityClass = TypeSpec.classBuilder(entityName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Entity.class)
                .addAnnotation(Data.class)
                .addAnnotation(Builder.class)
                .addAnnotation(AllArgsConstructor.class)
                .addAnnotation(NoArgsConstructor.class)
                .addAnnotation(AnnotationSpec.builder(EqualsAndHashCode.class)
                        .addMember("callSuper", "$L", false)
                        .build())
                .addField(id)
                .addFields(fieldSpecs)
                .superclass(GeneratorUtils.getEntitySuperClass(auditable))
                .addJavadoc(CodeBlock
                        .builder()
                        .add("@author JRolamo Code Generator")
                        .build())
                .build();

        JavaFile javaFile = JavaFile.builder(GeneratorUtils.DOMAIN_PACKAGE, entityClass)
                .indent(GeneratorUtils.DEFAULT_INDENTATION)
                .build();

        GeneratorUtils.save(javaFile);
    }
}

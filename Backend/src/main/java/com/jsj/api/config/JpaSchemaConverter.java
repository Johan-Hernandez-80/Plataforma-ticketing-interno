/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.config;

import com.fasterxml.jackson.databind.type.SimpleType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Juan José Molano Franco
 */
public class JpaSchemaConverter implements ModelConverter {

    private static final Map<Class<?>, String> EXAMPLES = Map.of(
            String.class, "Example text",
            Long.class, "1",
            Integer.class, "1",
            LocalDateTime.class, "2025-01-01T12:00:00"
    );

    @SuppressWarnings("unchecked")
    @Override
    public Schema<?> resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        if (type == null || type.getType() == null) {
            return chain.hasNext() ? chain.next().resolve(type, context, chain) : null;
        }

        Class<?> cls = null;
        if (type.getType() instanceof Class<?>) {
            cls = (Class<?>) type.getType();
        } else if (type.getType() instanceof SimpleType st) {
            cls = (Class<?>) st.getRawClass();
        }

        if (cls == null) {
            return chain.hasNext() ? chain.next().resolve(type, context, chain) : null;
        }

        Schema<?> schema = chain.hasNext() ? chain.next().resolve(type, context, chain) : null;
        if (schema == null) {
            return null;
        }

        // --- Class-level description ---
        if (schema.getDescription() == null) {
            schema.setDescription(buildClassDescription(cls));
        }

        for (Field field : cls.getDeclaredFields()) {
            Schema<?> prop = (Schema<?>) schema.getProperties().get(field.getName());
            if (prop == null) {
                continue;
            }

            // Description from field name
            prop.setDescription(toEnglishDescription(field.getName()));

            // Example based on type
            prop.setExample(EXAMPLES.getOrDefault(field.getType(), "Example value"));

            // Temporal fields
            if (field.getType().equals(LocalDateTime.class) || field.getType().equals(Date.class)) {
                prop.setFormat("date-time");
            }

            // JPA column annotations
            Column col = field.getAnnotation(Column.class);
            if (col != null) {
                if (col.length() > 0) {
                    prop.setDescription(prop.getDescription() + " (max length " + col.length() + ")");
                }
                if (!col.nullable()) {
                    prop.setNullable(false);
                    schema.addRequiredItem(field.getName());
                }
            }

            // Bean Validation @NotNull
            if (field.getAnnotation(NotNull.class) != null) {
                prop.setNullable(false);
                schema.addRequiredItem(field.getName());
            }

            // Special case for role
            if (field.getName().equalsIgnoreCase("rol")) {
                ((Schema<String>) prop).setEnum(Arrays.asList("admin", "agente", "usuario"));
                prop.setExample("usuario");
            }
        }
        return schema;
    }

    private String toEnglishDescription(String fieldName) {
        return fieldName.replaceAll("([A-Z])", " $1").toLowerCase().trim();
    }

    private String buildClassDescription(Class<?> cls) {
        String name = cls.getSimpleName();
        String base = name.replaceAll("DTO$", ""); // strip DTO

        String action = "data";
        if (base.toLowerCase().contains("create")) {
            action = "data for creating";
            base = base.replaceAll("(?i)create", "");
        } else if (base.toLowerCase().contains("update")) {
            action = "data for updating";
            base = base.replaceAll("(?i)update", "");
        } else if (base.toLowerCase().contains("response") || base.toLowerCase().contains("res")) {
            action = "data for responding with";
            base = base.replaceAll("(?i)response", "").replaceAll("(?i)res", "");
        }

        base = base.replaceAll("([A-Z])", " $1").toLowerCase().trim();
        return action + " a " + base;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.config;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customizeJwtAuth() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

    @Bean
    public OpenApiCustomizer globalForbiddenResponse() {
        return openApi -> openApi.getPaths().values().forEach(pathItem
                -> pathItem.readOperations().forEach(op -> {
                    op.getResponses().addApiResponse("403",
                            new ApiResponse().description("Acceso denegado. Token inválido o expirado."));
                })
        );
    }

    @Bean
    public OpenApiCustomizer dynamicDocsCustomizer() {
        return openApi -> {
            if (openApi.getComponents() == null || openApi.getComponents().getSchemas() == null) {
                return;
            }

            openApi.getComponents().getSchemas().forEach((schemaName, schema) -> {
                if (schema.getProperties() == null) {
                    return;
                }

                String entity = schemaName.replace("DTO", "").toLowerCase();

                schema.getProperties().forEach((propName, propSchema) -> {
                    Schema<?> prop = (Schema<?>) propSchema;
                    String field = String.valueOf(propName);

                    // Set description if missing
                    if (prop.getDescription() == null || prop.getDescription().isBlank()) {
                        switch (field) {
                            case "id" ->
                                prop.setDescription("Identificador único del " + entity + ".");
                            case "usuarioId" ->
                                prop.setDescription("ID del usuario relacionado con el " + entity + ".");
                            case "categoriaId" ->
                                prop.setDescription("ID de la categoría relacionada con el " + entity + ".");
                            case "agenteId" ->
                                prop.setDescription("ID del agente asignado al " + entity + ".");
                            case "titulo" ->
                                prop.setDescription("Título del " + entity + ".");
                            case "descripcion" ->
                                prop.setDescription("Descripción del " + entity + ".");
                            case "prioridad" ->
                                prop.setDescription("Prioridad del " + entity + " (Urgente, Importante, Programado).");
                            case "estado" ->
                                prop.setDescription("Estado del " + entity + " (Pendiente, En progreso, Cerrado).");
                            case "fechaCreacion" ->
                                prop.setDescription("Fecha de creación del " + entity + ".");
                            case "fechaCierre" ->
                                prop.setDescription("Fecha de cierre del " + entity + ".");
                            default ->
                                prop.setDescription("Atributo " + propName + " del " + entity + ".");
                        }
                    }

                    // Set example
                    switch (field) {
                        case "id", "usuarioId", "categoriaId", "agenteId" ->
                            prop.setExample(1);
                        case "titulo" ->
                            prop.setExample("Problema de red");
                        case "descripcion" ->
                            prop.setExample("No hay conexión a internet");
                        case "prioridad" ->
                            prop.setExample("Urgente");
                        case "estado" ->
                            prop.setExample("Pendiente");
                        case "fechaCreacion" ->
                            prop.setExample("2025-10-14 09:30:00");
                        case "fechaCierre" ->
                            prop.setExample("2025-10-16 16:45:00");
                        default ->
                            prop.setExample(null);
                    }
                });
            });
        };
    }

}

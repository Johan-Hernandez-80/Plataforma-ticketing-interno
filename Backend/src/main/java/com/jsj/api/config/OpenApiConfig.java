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
                            new ApiResponse().description("Forbidden – insufficient permissions"));
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

                    String field = String.valueOf(propName); // force it to String

                    if (prop.getDescription() != null && !prop.getDescription().isBlank()) {
                        return;
                    }

                    switch (field) {
                        case "id" ->
                            prop.setDescription("Unique identifier of the " + entity + ".");
                        case "fechaCreacion" ->
                            prop.setDescription("Creation timestamp of the " + entity + ".");
                        case "fechaCierre" ->
                            prop.setDescription("Closing timestamp of the " + entity + ".");
                        case "usuarioId" ->
                            prop.setDescription("Identifier of the user related to the " + entity + ".");
                        case "categoriaId" ->
                            prop.setDescription("Identifier of the category related to the " + entity + ".");
                        case "rolId" ->
                            prop.setDescription("Identifier of the role related to the " + entity + ".");
                        case "agenteId" ->
                            prop.setDescription("Identifier of the agent related to the " + entity + ".");
                        case "estado" ->
                            prop.setDescription("Current status of the " + entity + ".");
                        case "descripcion" ->
                            prop.setDescription("Detailed description of the " + entity + ".");
                        case "titulo" ->
                            prop.setDescription("Title of the " + entity + ".");
                        case "comentario" ->
                            prop.setDescription("Comment content.");
                        case "mensaje" ->
                            prop.setDescription("Notification message.");
                        case "email" ->
                            prop.setDescription("Email address of the usuario.");
                        case "password", "contrasena" ->
                            prop.setDescription("Password of the usuario.");
                        case "nombre" ->
                            prop.setDescription("Name of the " + entity + ".");
                        case "departamento" ->
                            prop.setDescription("Department of the usuario.");
                        case "estadoAnterior" ->
                            prop.setDescription("Previous status of the ticket.");
                        case "estadoNuevo" ->
                            prop.setDescription("New status of the ticket.");
                        case "prioridad" ->
                            prop.setDescription("Priority of the " + entity + " (ABIERTO, EN PROGRESO, RESUELTO, CERRADO).");
                        default ->
                            prop.setDescription("Attribute " + propName + " of the " + entity + ".");
                    }
                });
            });
        };
    }

}

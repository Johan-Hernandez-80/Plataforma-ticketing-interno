/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.config;

import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomizer customizeGenericCrud() {
        return (OpenAPI openApi) -> {
            openApi.getPaths().forEach((path, pathItem) -> {
                pathItem.readOperations().forEach(op -> {
                    String summary = op.getSummary();
                    if (summary != null && summary.contains("entity")) {
                        String entityName = inferEntityNameFromPath(path);
                        op.setSummary(summary.replace("entity", entityName));
                    }
                });
            });
        };
    }

    private String inferEntityNameFromPath(String path) {
        if (path.startsWith("/asignaciones")) return "Asignacion";
        if (path.startsWith("/usuarios")) return "Usuario";
        return "Entity";
    }

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
}

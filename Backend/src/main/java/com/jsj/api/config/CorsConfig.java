/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Juan José Molano Franco
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // todas las rutas de tu API
                .allowedOrigins(
                    "http://localhost:4200", // para desarrollo
                    "https://plataforma-ticketing-interno-frontend.onrender.com" // producción
                )
                .allowedMethods("*")       // GET, POST, PUT, DELETE, etc.
                .allowedHeaders("*")
                .allowCredentials(true);   // si usas cookies o auth
    }
}

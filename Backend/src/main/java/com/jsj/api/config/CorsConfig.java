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
        registry.addMapping("/**")          // todas las rutas
                .allowedOrigins("*")        // permite cualquier origen
                .allowedMethods("*")        // permite todos los métodos (GET, POST, PUT, DELETE...)
                .allowedHeaders("*")        // permite todos los headers
                .allowCredentials(false);   // debe ser false si allowedOrigins es "*" 
    }
}

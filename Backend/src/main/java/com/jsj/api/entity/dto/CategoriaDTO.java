/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Juan José Molano Franco
 */
@Data
@Schema(
        description = "DTO for Categoria. Only includes attributes the current user has permission to view. Field visibility depends on the user's roles and permissions."
)
public class CategoriaDTO {

    @Schema(example = "1")
    private Long id;
    
    @Schema(example = "Electrónica")
    @Size(max = 255, message = "La categoría no puede superar 255 caracteres")
    private String nombre;
    
    @Schema(example = "Casos relacionados con dispositivos electrónicos")
    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 *
 * @author Juan José Molano Franco
 */
@Data
@Schema(
        description = "DTO para usuario. Solo incluye los atributos que el usuario autenticado tiene permiso para usar. La visibilidad de los campos depende del rol y permisos del usuario."
)
public class UsuarioDTO {

    private Long id;
    private Long rolId;
    private String nombre;
    @NotBlank(message = "El correo personal es obligatorio")
    @Email(message = "El correo personal no es válido")
    @Pattern(regexp = "\\S+", message = "El correo personal no debe contener espacios en blanco")
    private String emailPersonal;
    @NotBlank(message = "El correo corporativo es obligatorio")
    @Email(message = "El correo corporativo no es válido")
    @Pattern(regexp = "\\S+", message = "El correo corporativo no debe contener espacios en blanco")
    private String emailCorporativo;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "\\S+", message = "La contraseña no debe contener espacios en blanco")
    private String contrasena;
    private String departamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}

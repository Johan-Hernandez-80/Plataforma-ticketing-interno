/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Juan José Molano Franco
 */
@Data
@Schema(
        description = "DTO para Comentario. Solo incluye los atributos a los que el usuario tiene permiso de ver. La visibilidad de campos depende del rol del usuario."
)
public class ComentarioDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "1")
    private Long ticketId;

    @Schema(example = "1")
    @NotNull
    private Long usuarioId;
    
    @Schema(example = "Se encontró que la impresora estaba desconectada")
    @Size(max = 1000, message = "El comentario no puede superar 1000 caracteres")
    private String comentario;
    
    @Schema(example = "2025-10-19 13:15:01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @Schema(example = "Juan Alberto")
    @Size(max = 255, message = "El nombre no puede superar 1000 caracteres")
    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}

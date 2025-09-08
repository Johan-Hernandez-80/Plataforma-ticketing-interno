/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dto;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Juan José Molano Franco
 */
@Data
@Schema(
    description = "DTO for Asignacion. Only includes attributes the current user has permission to view. Field visibility depends on the user's roles and permissions."
)
public class AsignacionDTO {

    private Long id;
    private Long ticketId;
    private Long agenteId;
    private LocalDateTime fechaCreacion;

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

    public Long getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(Long agenteId) {
        this.agenteId = agenteId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}

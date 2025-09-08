/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Juan José Molano Franco
 */
@Data
@Entity
@Table(name = "historial_tickets")
public class HistorialTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Column(nullable = false, length = 255)
    private String estadoAnterior;

    @Column(nullable = false, length = 255)
    private String estadoNuevo;

    @Column(nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(String estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

/**
 *
 * @author Juan José Molano Franco
 */
@Data
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 225)
    private String titulo;

    @Column(nullable = false, length = 225)
    private String descripcion;

    @Column(nullable = false, length = 225)
    private String prioridad;

    @Column(nullable = false, length = 225)
    private String estado;

    @Column(nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    // Las entidadema que tienen al ticket
    @OneToMany(mappedBy = "ticket")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "ticket")
    private List<Asignacion> asignaciones;

    @OneToMany(mappedBy = "ticket")
    private List<HistorialTicket> historial;

}

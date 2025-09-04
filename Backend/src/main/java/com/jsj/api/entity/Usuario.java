/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.Data;

/**
 *
 * @author Juan José Molano Franco
 */
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 225)
    private String nombre;

    @Column(unique = true, length = 225)
    private String emailPersonal;

    @Column(nullable = false, unique = true, length = 225)
    private String emailCorporativo;

    @Column(nullable = false, length = 225)
    private String contrasena;

    @Column(nullable = false, length = 225)
    private String rol;

    @Column(nullable = false, length = 225)
    private String departamento;

    // Las entidadema que tienen al usuario
    @OneToMany(mappedBy = "usuario")
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "agente")
    private List<Asignacion> asignaciones = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Notificacion> notificaciones = new ArrayList<>();

}

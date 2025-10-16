/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.repository;

import com.jsj.api.entity.Comentario;
import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dto.TicketDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Juan José Molano Franco
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("""
        SELECT t FROM Ticket t
        WHERE (:estado IS NULL OR t.estado = :estado)
        AND (:prioridad IS NULL OR t.prioridad = :prioridad)
        AND (:usuarioId IS NULL OR t.usuarioId = :usuarioId)
    """)
    List<Ticket> findTickets(
            @Param("estado") String estado,
            @Param("prioridad") String prioridad,
            @Param("usuarioId") Long usuarioId);

    @Query("""
        SELECT t FROM Ticket t
        WHERE (:estado IS NULL OR t.estado = :estado)
        AND (:prioridad IS NULL OR t.prioridad = :prioridad)
        AND (:usuarioId IS NULL OR t.usuarioId = :usuarioId)
        AND (:fechaCreacion IS NULL OR DATE(t.fechaCreacion) = :fechaCreacion)
    """)
    List<Ticket> findTickets(
            @Param("estado") String estado,
            @Param("prioridad") String prioridad,
            @Param("usuarioId") Long usuarioId,
            @Param("fechaCreacion") LocalDate fechaCreacion);

}

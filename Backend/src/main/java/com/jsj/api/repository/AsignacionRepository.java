/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.repository;

import com.jsj.api.entity.Asignacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Juan José Molano Franco
 */
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {

    Optional<Asignacion> findByAgenteIdAndTicketId(Long agenteId, Long ticketId);

    boolean existsByAgenteIdAndTicketId(Integer agenteId, Integer ticketId);

}

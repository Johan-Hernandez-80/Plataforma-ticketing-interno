/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.repository;

import com.jsj.api.entity.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Juan José Molano Franco
 */
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByTicketIdOrderByFechaCreacionAsc(Long ticketId);

}

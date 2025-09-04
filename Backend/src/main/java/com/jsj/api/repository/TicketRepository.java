/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.repository;

import com.jsj.api.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Juan José Molano Franco
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.HistorialTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public interface HistorialTicketDAO extends JpaRepository<HistorialTicket, Long> {

}

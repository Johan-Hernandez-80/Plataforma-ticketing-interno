/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.HistorialTicket;
import com.jsj.api.entity.dto.HistorialTicketDTO;
import com.jsj.api.entity.filter.HistorialTicketFilter;
import com.jsj.api.entity.mapper.HistorialTicketMapper;
import com.jsj.api.repository.HistorialTicketRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class HistorialTicketDAO extends BaseDAO<HistorialTicket, Long, HistorialTicketDTO, HistorialTicketMapper, HistorialTicketFilter, HistorialTicketRepository>{

    public HistorialTicketDAO(HistorialTicketMapper mapper, HistorialTicketFilter filter, HistorialTicketRepository repo) {
        super(mapper, filter, repo);
    }

}

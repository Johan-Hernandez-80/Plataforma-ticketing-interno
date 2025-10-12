/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.entity.filter.TicketFilter;
import com.jsj.api.entity.mapper.TicketMapper;
import com.jsj.api.repository.TicketRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class TicketDAO extends BaseDAO<Ticket, Long, TicketDTO, TicketMapper, TicketFilter, TicketRepository> {

    public TicketDAO(TicketMapper mapper, TicketFilter filter, TicketRepository repo) {
        super(mapper, filter, repo);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.repository.TicketRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class TicketService extends BaseService<Ticket, Long, TicketDTO, TicketDAO> {

    public TicketService(TicketDAO dao) {
        super(dao);
    }

}

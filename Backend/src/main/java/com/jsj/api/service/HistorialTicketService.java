/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.HistorialTicket;
import com.jsj.api.entity.dao.HistorialTicketDAO;
import com.jsj.api.entity.dto.HistorialTicketDTO;
import com.jsj.api.repository.HistorialTicketRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class HistorialTicketService extends BaseService<HistorialTicket, Long, HistorialTicketDTO, HistorialTicketDAO> {

    public HistorialTicketService(HistorialTicketDAO dao) {
        super(dao);
    }

}

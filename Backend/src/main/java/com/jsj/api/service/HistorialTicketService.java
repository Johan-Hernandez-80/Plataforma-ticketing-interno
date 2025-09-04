/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.util.BaseServiceImpl;
import com.jsj.api.entity.HistorialTicket;
import com.jsj.api.repository.HistorialTicketRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class HistorialTicketService extends BaseServiceImpl<HistorialTicket, Long> {

    public HistorialTicketService(HistorialTicketRepository repo) {
        super(repo);
    }
}

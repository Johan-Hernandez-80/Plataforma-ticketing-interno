/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class AdminService {

    private final TicketDAO ticketDao;
    private final UsuarioDAO usuarioDao;

    public AdminService(TicketDAO ticketDao, UsuarioDAO usuarioDao) {
        this.ticketDao = ticketDao;
        this.usuarioDao = usuarioDao;
    }
    
    

    public long countTicketsAbiertos() {
        return ticketDao.countTicketsAbiertos();
    }

    public long countTicketsCerrados() {
        return ticketDao.countTicketsCerrados();
    }

    public long countEmpleadosActivos() {
        return usuarioDao.countEmpleadosActivos();
    }

    public long countAgentesActivos() {
        return usuarioDao.countAgentesActivos();
    }
}

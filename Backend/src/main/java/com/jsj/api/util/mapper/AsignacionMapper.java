/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.util.mapper;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.TicketService;
import com.jsj.api.service.UsuarioService;
import com.jsj.api.util.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class AsignacionMapper implements BaseMapper<Asignacion, AsignacionDTO> {
    
    private final TicketService ticketService;
    private final UsuarioService usuarioService;

    public AsignacionMapper(TicketService ticketService, UsuarioService usuarioService) {
        this.ticketService = ticketService;
        this.usuarioService = usuarioService;
    }

    @Override
    public AsignacionDTO toDTO(Asignacion entity, Set<String> permissions) {
        
        AsignacionDTO dto = new AsignacionDTO();
        
        if (permissions.contains("view_asignacion_id") && entity.getId() != null) {dto.setId(entity.getId());}
        if (permissions.contains("view_asignacion_ticket_id") && entity.getTicket() != null) {dto.setTicketId(entity.getTicket().getId());}
        if (permissions.contains("view_asignacion_agente_id") && entity.getAgente() != null) {dto.setAgenteId(entity.getAgente().getId());}
        if (permissions.contains("view_asignacion_fecha_creacion") && entity.getFechaCreacion() != null) {dto.setFechaCreacion(entity.getFechaCreacion());}
        
        return dto;
        
    }

    @Override
    public Asignacion toEntity(AsignacionDTO dto, Set<String> permissions) {
        
        Asignacion entity = new Asignacion();
        
        if (permissions.contains("update_asignacion_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_asignacion_ticket_id") && dto.getTicketId() != null) {
            Ticket ticket = ticketService.findById(dto.getTicketId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
            entity.setTicket(ticket);
        }
        if (permissions.contains("update_asignacion_agente_id") && dto.getAgenteId() != null) {
            Usuario agente = usuarioService.findById(dto.getAgenteId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
            entity.setAgente(agente);
        }
        if (permissions.contains("update_asignacion_fecha_creacion") && dto.getFechaCreacion() != null) {entity.setFechaCreacion(dto.getFechaCreacion());}
        
        return entity;
        
    }

    @Override
    public void updateEntityFromDTO(AsignacionDTO dto, Asignacion entity, Set<String> permissions) {
        
        if (permissions.contains("update_asignacion_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_asignacion_ticket_id") && dto.getTicketId() != null) {
            Ticket ticket = ticketService.findById(dto.getTicketId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
            entity.setTicket(ticket);
        }
        if (permissions.contains("update_asignacion_agente_id") && dto.getAgenteId() != null) {
            Usuario agente = usuarioService.findById(dto.getAgenteId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
            entity.setAgente(agente);
        }
        if (permissions.contains("update_asignacion_fecha_creacion") && dto.getFechaCreacion() != null) {entity.setFechaCreacion(dto.getFechaCreacion());}
        
    }

}

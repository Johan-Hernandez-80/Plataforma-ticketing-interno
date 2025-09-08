/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.util.mapper;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.TicketService;
import com.jsj.api.util.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class HistorialTicketMapper implements BaseMapper<HistorialTicket, HistorialTicketDTO> {
    
    private final TicketService ticketService;

    public HistorialTicketMapper(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public HistorialTicketDTO toDTO(HistorialTicket entity, Set<String> permissions) {
        
        HistorialTicketDTO dto = new HistorialTicketDTO();
        
        if (permissions.contains("view_historial_ticket_id") && entity.getId() != null) {dto.setId(entity.getId());}
        if (permissions.contains("view_historial_ticket_ticket_id") && entity.getTicket() != null) {dto.setTicketId(entity.getTicket().getId());}
        if (permissions.contains("view_historial_ticket_estado_anterior") && entity.getEstadoAnterior() != null) {dto.setEstadoAnterior(entity.getEstadoAnterior());}
        if (permissions.contains("view_historial_ticket_estado_nuevo") && entity.getEstadoNuevo() != null) {dto.setEstadoNuevo(entity.getEstadoNuevo());}
        if (permissions.contains("view_historial_ticket_fecha_creacion") && entity.getFechaCreacion() != null) {dto.setFechaCreacion(entity.getFechaCreacion());}
        
        return dto;
    
    }

    @Override
    public HistorialTicket toEntity(HistorialTicketDTO dto, Set<String> permissions) {
        
        HistorialTicket entity = new HistorialTicket();
        
        if (permissions.contains("update_historial_ticket_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_historial_ticket_ticket_id") && dto.getTicketId() != null) {
            Ticket ticket = ticketService.findById(dto.getTicketId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
            entity.setTicket(ticket);
        }
        if (permissions.contains("update_historial_ticket_estado_anterios") && dto.getEstadoAnterior() != null) {entity.setEstadoAnterior(dto.getEstadoAnterior());}
        if (permissions.contains("update_historial_ticket_estado_nuevo") && dto.getEstadoNuevo() != null) {entity.setEstadoNuevo(dto.getEstadoNuevo());}
        if (permissions.contains("update_historial_ticket_fecha_creacion") && dto.getFechaCreacion() != null) {entity.setFechaCreacion(dto.getFechaCreacion());}

        return entity;
        
    }

    @Override
    public void updateEntityFromDTO(HistorialTicketDTO dto, HistorialTicket entity, Set<String> permissions) {

        if (permissions.contains("update_historial_ticket_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_historial_ticket_ticket_id") && dto.getTicketId() != null) {
            Ticket ticket = ticketService.findById(dto.getTicketId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
            entity.setTicket(ticket);
        }
        if (permissions.contains("update_historial_ticket_estado_anterios") && dto.getEstadoAnterior() != null) {entity.setEstadoAnterior(dto.getEstadoAnterior());}
        if (permissions.contains("update_historial_ticket_estado_nuevo") && dto.getEstadoNuevo() != null) {entity.setEstadoNuevo(dto.getEstadoNuevo());}
        if (permissions.contains("update_historial_ticket_fecha_creacion") && dto.getFechaCreacion() != null) {entity.setFechaCreacion(dto.getFechaCreacion());}

    }

}

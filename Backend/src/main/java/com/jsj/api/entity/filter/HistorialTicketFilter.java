/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.TicketService;
import com.jsj.api.entity.mapper.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class HistorialTicketFilter extends BaseFilter<HistorialTicket, HistorialTicketDTO> {
    
    private final TicketService ticketService;

    public HistorialTicketFilter(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public HistorialTicketDTO filterDTO(HistorialTicketDTO dto) {
        
        if (!permissions.contains("view_historial_ticket_id")) { dto.setId(null); }
        if (!permissions.contains("view_historial_ticket_ticket_id")) { dto.setTicketId(null); }
        if (!permissions.contains("view_historial_ticket_estado_anterior")) { dto.setEstadoAnterior(null); }
        if (!permissions.contains("view_historial_ticket_estado_nuevo")) { dto.setEstadoNuevo(null); }
        if (!permissions.contains("view_historial_ticket_fecha_creacion")) { dto.setFechaCreacion(null); }
        
        return dto;
    }

    @Override
    public HistorialTicket filterEntity(HistorialTicket entity) {
        
        if (!permissions.contains("update_historial_ticket_id")) { entity.setId(null); }
        if (!permissions.contains("update_historial_ticket_ticket_id")) { entity.setTicket(null); }
        if (!permissions.contains("update_historial_ticket_estado_anterior")) { entity.setEstadoAnterior(null); }
        if (!permissions.contains("update_historial_ticket_estado_nuevo")) { entity.setEstadoNuevo(null); }
        if (!permissions.contains("update_historial_ticket_fecha_creacion")) { entity.setFechaCreacion(null); }
        
        return entity;
    }
}

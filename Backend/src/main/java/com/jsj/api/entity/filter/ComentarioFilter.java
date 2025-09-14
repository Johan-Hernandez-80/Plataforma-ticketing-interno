/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.TicketService;
import com.jsj.api.service.UsuarioService;
import com.jsj.api.entity.mapper.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class ComentarioFilter implements BaseFilter<Comentario, ComentarioDTO> {
    
    private final TicketService ticketService;
    private final UsuarioService usuarioService;

    public ComentarioFilter(TicketService ticketService, UsuarioService usuarioService) {
        this.ticketService = ticketService;
        this.usuarioService = usuarioService;
    }

    @Override
    public ComentarioDTO filterDTO(ComentarioDTO dto, Set<String> permissions) {
        
        if (!permissions.contains("view_comentario_id")) { dto.setId(null); }
        if (!permissions.contains("view_comentario_ticket_id")) { dto.setTicketId(null); }
        if (!permissions.contains("view_comentario_usuario_id")) { dto.setUsuarioId(null); }
        if (!permissions.contains("view_comentario_comentario")) { dto.setComentario(null); }
        if (!permissions.contains("view_comentario_fecha_creacion")) { dto.setFechaCreacion(null); }
        
        return dto;
    }

    @Override
    public Comentario filterEntity(Comentario entity, Set<String> permissions) {
        
        if (!permissions.contains("update_comentario_id")) { entity.setId(null); }
        if (!permissions.contains("update_comentario_ticket_id")) { entity.setTicket(null); }
        if (!permissions.contains("update_comentario_usuario_id")) { entity.setUsuario(null); }
        if (!permissions.contains("update_comentario_comentario")) { entity.setComentario(null); }
        if (!permissions.contains("update_comentario_fecha_creacion")) { entity.setFechaCreacion(null); }
        
        return entity;
    }
}

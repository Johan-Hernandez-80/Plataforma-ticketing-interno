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
public class ComentarioMapper implements BaseMapper<Comentario, ComentarioDTO> {
    
    private final TicketService ticketService;
    private final UsuarioService usuarioService;

    public ComentarioMapper(TicketService ticketService, UsuarioService usuarioService) {
        this.ticketService = ticketService;
        this.usuarioService = usuarioService;
    }
    
    @Override
    public ComentarioDTO toDTO(Comentario entity, Set<String> permissions) {
        
        ComentarioDTO dto = new ComentarioDTO();
        
        if (permissions.contains("view_comentario_id") && entity.getId() != null) {dto.setId(entity.getId());}
        if (permissions.contains("view_comentario_ticket_id") && entity.getTicket() != null) {dto.setTicketId(entity.getTicket().getId());}
        if (permissions.contains("view_comentario_usuario_id") && entity.getUsuario() != null) {dto.setUsuarioId(entity.getUsuario().getId());}
        if (permissions.contains("view_comentario_comentario") && entity.getComentario() != null) {dto.setComentario(entity.getComentario());}
        if (permissions.contains("view_comentario_fecha_creacion") && entity.getFechaCreacion() != null) {dto.setFechaCreacion(entity.getFechaCreacion());}
        
        return dto;

    }

    @Override
    public Comentario toEntity(ComentarioDTO dto, Set<String> permissions) {

        Comentario entity = new Comentario();
        
        if (permissions.contains("update_comentario_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_comentario_ticket_id") && dto.getTicketId() != null) {
            Ticket ticket = ticketService.findById(dto.getTicketId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
            entity.setTicket(ticket);
        }
        if (permissions.contains("update_comentario_usuario_id") && dto.getUsuarioId() != null) {
            Usuario usuario = usuarioService.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
            entity.setUsuario(usuario);
        }
        if (permissions.contains("update_comentario_comentario") && dto.getComentario() != null) {entity.setComentario(dto.getComentario());}
        if (permissions.contains("update_comentario_fecha_creacion") && dto.getFechaCreacion() != null) {entity.setFechaCreacion(dto.getFechaCreacion());}

        return entity;
        
    }

    @Override
    public void updateEntityFromDTO(ComentarioDTO dto, Comentario entity, Set<String> permissions) {
        
        if (permissions.contains("update_comentario_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_comentario_ticket_id") && dto.getTicketId() != null) {
            Ticket ticket = ticketService.findById(dto.getTicketId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
            entity.setTicket(ticket);
        }
        if (permissions.contains("update_comentario_usuario_id") && dto.getUsuarioId() != null) {
            Usuario usuario = usuarioService.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
            entity.setUsuario(usuario);
        }
        if (permissions.contains("update_comentario_comentario") && dto.getComentario() != null) {entity.setComentario(dto.getComentario());}
        if (permissions.contains("update_comentario_fecha_creacion") && dto.getFechaCreacion() != null) {entity.setFechaCreacion(dto.getFechaCreacion());}

    }

}

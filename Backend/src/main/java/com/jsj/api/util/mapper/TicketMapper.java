/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.util.mapper;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.CategoriaService;
import com.jsj.api.service.UsuarioService;
import com.jsj.api.util.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class TicketMapper implements BaseMapper<Ticket, TicketDTO> {
    
    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;

    public TicketMapper(UsuarioService usuarioService, CategoriaService categoriaService) {
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
    }
    
    @Override
    public TicketDTO toDTO(Ticket entity, Set<String> permissions) {
        
        TicketDTO dto = new TicketDTO();
        
        if (permissions.contains("view_ticket_id") && entity.getId() != null) {dto.setId(entity.getId());}
        if (permissions.contains("view_ticket_usuario_id") && entity.getUsuario() != null) {dto.setUsuarioId(entity.getUsuario().getId());}
        if (permissions.contains("view_ticket_categoria_id") && entity.getCategoria() != null) {dto.setCategoriaId(entity.getCategoria().getId());}
        if (permissions.contains("view_ticket_titulo") && entity.getTitulo() != null) {dto.setTitulo(entity.getTitulo());}
        if (permissions.contains("view_ticket_descripcion") && entity.getDescripcion() != null) {dto.setDescripcion(entity.getDescripcion());}
        if (permissions.contains("view_ticket_prioridad") && entity.getPrioridad() != null) {dto.setPrioridad(entity.getPrioridad());}
        if (permissions.contains("view_ticket_estado") && entity.getEstado() != null) {dto.setEstado(entity.getEstado());}
        if (permissions.contains("view_ticket_fecha_creacion") && entity.getFechaCreacion() != null) {dto.setFechaCreacion(entity.getFechaCreacion());}
        if (permissions.contains("view_ticket_fecha_cierre") && entity.getFechaCierre() != null) {dto.setFechaCierre(entity.getFechaCierre());}
        
        return dto;
        
    }

    @Override
    public Ticket toEntity(TicketDTO dto, Set<String> permissions) {
        
        Ticket entity = new Ticket();
        
        if (permissions.contains("update_ticket_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_ticket_usuario_id") && dto.getUsuarioId() != null) {
            Usuario usuario = usuarioService.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
            entity.setUsuario(usuario);
        }
        if (permissions.contains("update_ticket_categoria_id") && dto.getCategoriaId() != null) {
            Categoria categoria = categoriaService.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria not found"));
            entity.setCategoria(categoria);
        }
        if (permissions.contains("update_ticket_titulo") && dto.getTitulo() != null) {entity.setTitulo(dto.getTitulo());}
        if (permissions.contains("update_ticket_descripcion") && dto.getDescripcion() != null) {entity.setDescripcion(dto.getDescripcion());}
        if (permissions.contains("update_ticket_prioridad") && dto.getPrioridad() != null) {entity.setPrioridad(dto.getPrioridad());}
        if (permissions.contains("update_ticket_estado") && dto.getEstado() != null) {entity.setEstado(dto.getEstado());}
        if (permissions.contains("update_ticket_fecha_creacion") && dto.getFechaCreacion() != null) {entity.setFechaCreacion(dto.getFechaCreacion());}
        if (permissions.contains("update_ticket_fecha_cierre") && dto.getFechaCierre() != null) {entity.setFechaCierre(dto.getFechaCierre());}
        
        return entity;
        
    }

    @Override
    public void updateEntityFromDTO(TicketDTO dto, Ticket entity, Set<String> permissions) {
     
        if (permissions.contains("update_ticket_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_ticket_usuario_id") && dto.getUsuarioId() != null) {
            Usuario usuario = usuarioService.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
            entity.setUsuario(usuario);
        }
        if (permissions.contains("update_ticket_categoria_id") && dto.getCategoriaId() != null) {
            Categoria categoria = categoriaService.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria not found"));
            entity.setCategoria(categoria);
        }
        if (permissions.contains("update_ticket_titulo") && dto.getTitulo() != null) {entity.setTitulo(dto.getTitulo());}
        if (permissions.contains("update_ticket_descripcion") && dto.getDescripcion() != null) {entity.setDescripcion(dto.getDescripcion());}
        if (permissions.contains("update_ticket_prioridad") && dto.getPrioridad() != null) {entity.setPrioridad(dto.getPrioridad());}
        if (permissions.contains("update_ticket_estado") && dto.getEstado() != null) {entity.setEstado(dto.getEstado());}
        if (permissions.contains("update_ticket_fecha_creacion") && dto.getFechaCreacion() != null) {entity.setFechaCreacion(dto.getFechaCreacion());}
        if (permissions.contains("update_ticket_fecha_cierre") && dto.getFechaCierre() != null) {entity.setFechaCierre(dto.getFechaCierre());}
        
    }

}

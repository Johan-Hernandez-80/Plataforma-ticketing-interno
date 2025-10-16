/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.CategoriaService;
import com.jsj.api.service.UsuarioService;
import com.jsj.api.entity.mapper.BaseMapper;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.security.CurrentUser;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class TicketFilter extends BaseFilter<Ticket, TicketDTO> {
    
    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;

    public TicketFilter(UsuarioService usuarioService, CategoriaService categoriaService) {
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
    }
    
    @Override
    public TicketDTO filterDTO(TicketDTO dto) {
        
        Set<String> permissions = CurrentUser.getPermissions();
        
        if (!permissions.contains("view_ticket_id")) {dto.setId(null);}
        if (!permissions.contains("view_ticket_usuario_id")) {dto.setUsuarioId(null);}
        if (!permissions.contains("view_ticket_categoria_id")) {dto.setCategoriaId(null);}
        if (!permissions.contains("view_ticket_titulo")) {dto.setTitulo(null);}
        if (!permissions.contains("view_ticket_descripcion")) {dto.setDescripcion(null);}
        if (!permissions.contains("view_ticket_prioridad")) {dto.setPrioridad(null);}
        if (!permissions.contains("view_ticket_estado")) {dto.setEstado(null);}
        if (!permissions.contains("view_ticket_fecha_creacion")) {dto.setFechaCreacion(null);}
        if (!permissions.contains("view_ticket_fecha_cierre")) {dto.setFechaCierre(null);}
        
        return dto;
        
    }

    @Override
    public Ticket filterEntityToSave(Ticket entity) {
        
        Set<String> permissions = CurrentUser.getPermissions();
        
        if (!permissions.contains("update_ticket_id")) {entity.setId(null);}
        if (!permissions.contains("update_ticket_usuario_id")) {entity.setUsuario(null);}
        if (!permissions.contains("update_ticket_categoria_id")) {entity.setCategoria(null);}
        if (!permissions.contains("update_ticket_titulo")) {entity.setTitulo(null);}
        if (!permissions.contains("update_ticket_descripcion")) {entity.setDescripcion(null);}
        if (!permissions.contains("update_ticket_prioridad")) {entity.setPrioridad(null);}
        if (!permissions.contains("update_ticket_estado")) {entity.setEstado(null);}
        if (!permissions.contains("update_ticket_fecha_creacion")) {entity.setFechaCreacion(null);}
        if (!permissions.contains("update_ticket_fecha_cierre")) {entity.setFechaCierre(null);}
        
        return entity;
        
    }

    @Override
    Ticket filterEntityToUpdate(Ticket entity, TicketDTO dto) throws InsufficientSavingPermissionsException {
        
        Set<String> permissions = CurrentUser.getPermissions();
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.util.mapper;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.UsuarioService;
import com.jsj.api.util.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class NotificacionMapper implements BaseMapper<Notificacion, NotificacionDTO> {
    
    private final UsuarioService usuarioService;

    public NotificacionMapper(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public NotificacionDTO toDTO(Notificacion entity, Set<String> permissions) {
        
        NotificacionDTO dto = new NotificacionDTO();

        if (permissions.contains("view_notificacion_id") && entity.getId() != null) {dto.setId(entity.getId());}
        if (permissions.contains("view_notificacion_usuario_id") && entity.getUsuario() != null) {dto.setUsuarioId(entity.getUsuario().getId());}
        if (permissions.contains("view_notificacion_mensaje") && entity.getMensaje() != null) {dto.setMensaje(entity.getMensaje());}
        if (permissions.contains("view_notificacion_fecha_creacion") && entity.getFechaCreacion() != null) {dto.setFechaCreacion(entity.getFechaCreacion());}

        return dto;
            
    }

    @Override
    public Notificacion toEntity(NotificacionDTO dto, Set<String> permissions) {
        
        Notificacion entity = new Notificacion();
        
        if (permissions.contains("update_notificacion_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_notificacion_usuario_id") && dto.getUsuarioId() != null) {
            Usuario usuario = usuarioService.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
            entity.setUsuario(usuario);
        }
        if (permissions.contains("update_notificacion_mensaje") && dto.getMensaje() != null) {entity.setMensaje(dto.getMensaje());}
        if (permissions.contains("update_notificacion_fecha_creacion") && dto.getFechaCreacion()!= null) {entity.setFechaCreacion(dto.getFechaCreacion());}
        
        return entity;
        
    }

    @Override
    public void updateEntityFromDTO(NotificacionDTO dto, Notificacion entity, Set<String> permissions) {
        
        if (permissions.contains("update_notificacion_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_notificacion_usuario_id") && dto.getUsuarioId() != null) {
            Usuario usuario = usuarioService.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
            entity.setUsuario(usuario);
        }
        if (permissions.contains("update_notificacion_mensaje") && dto.getMensaje() != null) {entity.setMensaje(dto.getMensaje());}
        if (permissions.contains("update_notificacion_fecha_creacion") && dto.getFechaCreacion()!= null) {entity.setFechaCreacion(dto.getFechaCreacion());}
        
    }

}

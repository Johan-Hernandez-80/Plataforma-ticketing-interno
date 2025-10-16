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
import com.jsj.api.exception.ImmutableFieldException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.security.CurrentUser;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class ComentarioFilter extends BaseFilter<Comentario, ComentarioDTO> {
    
    private static final Logger log = LoggerFactory.getLogger(UsuarioFilter.class);

    @Override
    public ComentarioDTO filterDTO(ComentarioDTO dto) {
        Set<String> permissions = CurrentUser.getPermissions();

        if (!permissions.contains("view_comentario_id")) {
            dto.setId(null);
        }
        if (!permissions.contains("view_comentario_ticket_id")) {
            dto.setTicketId(null);
        }
        if (!permissions.contains("view_comentario_usuario_id")) {
            dto.setUsuarioId(null);
        }
        if (!permissions.contains("view_comentario_comentario")) {
            dto.setComentario(null);
        }
        if (!permissions.contains("view_comentario_fecha_creacion")) {
            dto.setFechaCreacion(null);
        }

        return dto;
    }

    @Override
    public Comentario filterEntityToSave(Comentario entity) throws InsufficientSavingPermissionsException {
        Set<String> permissions = CurrentUser.getPermissions();
        
        log.info(permissions.toString());

        entity.setId(null);

        if (!permissions.contains("update_comentario_ticket_id")
                || !permissions.contains("update_comentario_usuario_id")
                || !permissions.contains("update_comentario_texto")) {
            throw new InsufficientSavingPermissionsException(
                    "No tiene permisos para guardar la entidad Comentario"
            );
        }

        return entity;
    }

    @Override
    public Comentario filterEntityToUpdate(Comentario entity, ComentarioDTO dto)
            throws InsufficientSavingPermissionsException, ImmutableFieldException {

        Set<String> permissions = CurrentUser.getPermissions();

        if (dto.getId() != null) {
            throw new ImmutableFieldException("No se puede cambiar la id");
        }

        if (dto.getTicketId() != null) {
            if (permissions.contains("update_comentario_ticket_id")) {
                entity.setTicketId(dto.getTicketId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar ticketId");
            }
        }

        if (dto.getUsuarioId() != null) {
            if (permissions.contains("update_comentario_usuario_id")) {
                entity.setUsuarioId(dto.getUsuarioId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar usuarioId");
            }
        }

        if (dto.getComentario() != null) {
            if (permissions.contains("update_comentario_texto")) {
                entity.setComentario(dto.getComentario());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar comentario");
            }
        }

        return entity;
    }
}

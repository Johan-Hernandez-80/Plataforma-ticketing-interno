/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.exception.ImmutableFieldException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.security.CurrentUser;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class NotificacionFilter extends BaseFilter<Notificacion, NotificacionDTO> {

    @Override
    public NotificacionDTO filterDTO(NotificacionDTO dto) {
        Set<String> permissions = CurrentUser.getPermissions();

        if (!permissions.contains("view_notificacion_id")) {
            dto.setId(null);
        }
        if (!permissions.contains("view_notificacion_usuario_id")) {
            dto.setUsuarioId(null);
        }
        if (!permissions.contains("view_notificacion_mensaje")) {
            dto.setMensaje(null);
        }
        if (!permissions.contains("view_notificacion_fecha_creacion")) {
            dto.setFechaCreacion(null);
        }

        return dto;
    }

    @Override
    public Notificacion filterEntityToSave(Notificacion entity) throws InsufficientSavingPermissionsException {
        Set<String> permissions = CurrentUser.getPermissions();
        
        entity.setId(null);
        
        entity.setFechaCreacion(null);

        if (!permissions.contains("update_notificacion_usuario_id")
                || !permissions.contains("update_notificacion_mensaje")) {
            throw new InsufficientSavingPermissionsException(
                    "No tiene permisos para guardar la entidad Notificacion"
            );
        }

        return entity;
    }

    @Override
    public Notificacion filterEntityToUpdate(Notificacion entity, NotificacionDTO dto)
            throws InsufficientSavingPermissionsException, ImmutableFieldException {

        Set<String> permissions = CurrentUser.getPermissions();

        if (dto.getId() != null) {
            throw new ImmutableFieldException("No se puede cambiar la id");
        }

        if (dto.getUsuarioId() != null) {
            if (permissions.contains("update_notificacion_usuario_id")) {
                entity.setUsuarioId(dto.getUsuarioId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar usuarioId");
            }
        }

        if (dto.getMensaje() != null) {
            if (permissions.contains("update_notificacion_mensaje")) {
                entity.setMensaje(dto.getMensaje());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar mensaje");
            }
        }

        return entity;
    }
}

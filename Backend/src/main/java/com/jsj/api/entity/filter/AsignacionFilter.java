/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.entity.mapper.BaseMapper;
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
public class AsignacionFilter extends BaseFilter<Asignacion, AsignacionDTO> {

    @Override
    public AsignacionDTO filterDTO(AsignacionDTO dto) {
        Set<String> permissions = CurrentUser.getPermissions();

        if (!permissions.contains("view_asignacion_id")) {
            dto.setId(null);
        }
        if (!permissions.contains("view_asignacion_ticket_id")) {
            dto.setTicketId(null);
        }
        if (!permissions.contains("view_asignacion_agente_id")) {
            dto.setAgenteId(null);
        }
        if (!permissions.contains("view_asignacion_fecha_creacion")) {
            dto.setFechaCreacion(null);
        }

        return dto;
    }

    @Override
    public Asignacion filterEntityToSave(Asignacion entity) throws InsufficientSavingPermissionsException {
        Set<String> permissions = CurrentUser.getPermissions();

        entity.setId(null);
        entity.setFechaCreacion(null);

        if (!permissions.contains("update_asignacion_ticket_id") ||
                !permissions.contains("update_asignacion_agente_id")) {
            throw new InsufficientSavingPermissionsException(
                    String.format("El usuario no tiene permisos para guardar la entidad %s", Asignacion.class)
            );
        }

        return entity;
    }

    @Override
    public Asignacion filterEntityToUpdate(Asignacion entity, AsignacionDTO dto) throws InsufficientSavingPermissionsException, ImmutableFieldException {
        Set<String> permissions = CurrentUser.getPermissions();

        if (dto.getId() != null && !dto.getId().equals(entity.getId())) {
            throw new ImmutableFieldException("No se puede cambiar la id de la asignación");
        }

        if (dto.getTicketId() != null) {
            if (permissions.contains("update_asignacion_ticket_id")) {
                entity.setTicketId(dto.getTicketId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el ticket de la asignación");
            }
        }

        if (dto.getAgenteId() != null) {
            if (permissions.contains("update_asignacion_agente_id")) {
                entity.setAgenteId(dto.getAgenteId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el agente de la asignación");
            }
        }

        return entity;
    }
}

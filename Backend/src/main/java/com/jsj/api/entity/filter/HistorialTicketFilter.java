/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.TicketService;
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
public class HistorialTicketFilter extends BaseFilter<HistorialTicket, HistorialTicketDTO> {

    private final TicketService ticketService;

    public HistorialTicketFilter(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public HistorialTicketDTO filterDTO(HistorialTicketDTO dto) {

        Set<String> permissions = CurrentUser.getPermissions();

        if (!permissions.contains("view_historial_ticket_id")) {
            dto.setId(null);
        }
        if (!permissions.contains("view_historial_ticket_ticket_id")) {
            dto.setTicketId(null);
        }
        if (!permissions.contains("view_historial_ticket_estado_anterior")) {
            dto.setEstadoAnterior(null);
        }
        if (!permissions.contains("view_historial_ticket_estado_nuevo")) {
            dto.setEstadoNuevo(null);
        }
        if (!permissions.contains("view_historial_ticket_fecha_creacion")) {
            dto.setFechaCreacion(null);
        }

        return dto;
    }

    @Override
    public HistorialTicket filterEntityToSave(HistorialTicket entity) throws InsufficientSavingPermissionsException {

        Set<String> permissions = CurrentUser.getPermissions();

        entity.setId(null);
        entity.setFechaCreacion(null);

        if (!permissions.contains("update_historial_ticket_ticket_id")
                || !permissions.contains("update_historial_ticket_estado_anterior")
                || !permissions.contains("update_historial_ticket_estado_nuevo")) {
            throw new InsufficientSavingPermissionsException(
                    String.format("El usuario no tiene permisos para guardar la entidad %s", HistorialTicket.class)
            );
        }

        return entity;
    }

    @Override
    HistorialTicket filterEntityToUpdate(HistorialTicket entity, HistorialTicketDTO dto) throws InsufficientSavingPermissionsException, ImmutableFieldException {

        Set<String> permissions = CurrentUser.getPermissions();

        if (dto.getId() != null) {
            throw new ImmutableFieldException("No se puede cambiar el id del historial");
        }

        if (dto.getTicketId() != null) {
            if (permissions.contains("update_historial_ticket_ticket_id")) {
                entity.setTicketId(dto.getTicketId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el ticket asociado");
            }
        }

        if (dto.getEstadoAnterior() != null) {
            if (permissions.contains("update_historial_ticket_estado_anterior")) {
                entity.setEstadoAnterior(dto.getEstadoAnterior());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el estado anterior");
            }
        }

        if (dto.getEstadoNuevo() != null) {
            if (permissions.contains("update_historial_ticket_estado_nuevo")) {
                entity.setEstadoNuevo(dto.getEstadoNuevo());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el estado nuevo");
            }
        }

        if (dto.getFechaCreacion() != null) {
            if (permissions.contains("update_historial_ticket_fecha_creacion")) {
                entity.setFechaCreacion(dto.getFechaCreacion());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la fecha de creación");
            }
        }

        return entity;
    }
}

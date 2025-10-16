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

        if (!permissions.contains("view_ticket_id")) {
            dto.setId(null);
        }
        if (!permissions.contains("view_ticket_usuario_id")) {
            dto.setUsuarioId(null);
        }
        if (!permissions.contains("view_ticket_categoria_id")) {
            dto.setCategoriaId(null);
        }
        if (!permissions.contains("view_ticket_titulo")) {
            dto.setTitulo(null);
        }
        if (!permissions.contains("view_ticket_descripcion")) {
            dto.setDescripcion(null);
        }
        if (!permissions.contains("view_ticket_prioridad")) {
            dto.setPrioridad(null);
        }
        if (!permissions.contains("view_ticket_estado")) {
            dto.setEstado(null);
        }
        if (!permissions.contains("view_ticket_fecha_creacion")) {
            dto.setFechaCreacion(null);
        }
        if (!permissions.contains("view_ticket_fecha_cierre")) {
            dto.setFechaCierre(null);
        }

        return dto;
    }

    @Override
    public Ticket filterEntityToSave(Ticket entity) throws InsufficientSavingPermissionsException {
        Set<String> permissions = CurrentUser.getPermissions();

        entity.setId(null);
        entity.setFechaCreacion(null);

        if (!permissions.contains("update_ticket_usuario_id")
                || !permissions.contains("update_ticket_categoria_id")
                || !permissions.contains("update_ticket_titulo")
                || !permissions.contains("update_ticket_descripcion")
                || !permissions.contains("update_ticket_prioridad")
                || !permissions.contains("update_ticket_estado")
                || !permissions.contains("update_ticket_fecha_creacion")
                || !permissions.contains("update_ticket_fecha_cierre")) {
            throw new InsufficientSavingPermissionsException(
                    String.format("El usuario no tiene permisos para guardar la entidad %s", Ticket.class.toString()));
        }

        return entity;
    }

    @Override
    public Ticket filterEntityToUpdate(Ticket entity, TicketDTO dto)
            throws InsufficientSavingPermissionsException, ImmutableFieldException {

        Set<String> permissions = CurrentUser.getPermissions();

        if (dto.getId() != null) {
            throw new ImmutableFieldException("No se puede cambiar la id del ticket");
        }

        if (dto.getUsuarioId() != null) {
            if (permissions.contains("update_ticket_usuario_id")) {
                entity.setUsuarioId(dto.getUsuarioId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la id del usuario del ticket");
            }
        }

        if (dto.getCategoriaId() != null) {
            if (permissions.contains("update_ticket_categoria_id")) {
                entity.setCategoriaId(dto.getCategoriaId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la id de la categoría del ticket");
            }
        }

        if (dto.getTitulo() != null) {
            if (permissions.contains("update_ticket_titulo")) {
                entity.setTitulo(dto.getTitulo());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el título del ticket");
            }
        }

        if (dto.getDescripcion() != null) {
            if (permissions.contains("update_ticket_descripcion")) {
                entity.setDescripcion(dto.getDescripcion());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la descripción del ticket");
            }
        }

        if (dto.getPrioridad() != null) {
            if (permissions.contains("update_ticket_prioridad")) {
                entity.setPrioridad(dto.getPrioridad());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la prioridad del ticket");
            }
        }

        if (dto.getEstado() != null) {
            if (permissions.contains("update_ticket_estado")) {
                entity.setEstado(dto.getEstado());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el estado del ticket");
            }
        }

        if (dto.getFechaCreacion() != null) {
            if (permissions.contains("update_ticket_fecha_creacion")) {
                entity.setFechaCreacion(dto.getFechaCreacion());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la fecha de creación del ticket");
            }
        }

        if (dto.getFechaCierre() != null) {
            if (permissions.contains("update_ticket_fecha_cierre")) {
                entity.setFechaCierre(dto.getFechaCierre());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la fecha de cierre del ticket");
            }
        }

        return entity;
    }
}

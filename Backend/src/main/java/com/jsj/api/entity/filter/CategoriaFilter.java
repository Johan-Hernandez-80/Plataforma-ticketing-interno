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
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class CategoriaFilter extends BaseFilter<Categoria, CategoriaDTO> {

    @Override
    public CategoriaDTO filterDTO(CategoriaDTO dto) {
        Set<String> permissions = CurrentUser.getPermissions();

        if (!permissions.contains("view_categoria_id")) {
            dto.setId(null);
        }
        if (!permissions.contains("view_categoria_nombre")) {
            dto.setNombre(null);
        }
        if (!permissions.contains("view_categoria_descripcion")) {
            dto.setDescripcion(null);
        }

        return dto;
    }

    @Override
    public Categoria filterEntityToSave(Categoria entity) throws InsufficientSavingPermissionsException {
        Set<String> permissions = CurrentUser.getPermissions();

        entity.setId(null);

        if (!permissions.contains("update_categoria_nombre") ||
                !permissions.contains("update_categoria_descripcion")) {
            throw new InsufficientSavingPermissionsException(
                    String.format("El usuario no tiene permisos para guardar la entidad %s", Categoria.class)
            );
        }

        return entity;
    }

    @Override
    public Categoria filterEntityToUpdate(Categoria entity, CategoriaDTO dto) throws InsufficientSavingPermissionsException, ImmutableFieldException {
        Set<String> permissions = CurrentUser.getPermissions();

        if (dto.getId() != null && !dto.getId().equals(entity.getId())) {
            throw new ImmutableFieldException("No se puede cambiar la id de la categoría");
        }

        if (dto.getNombre() != null) {
            if (permissions.contains("update_categoria_nombre")) {
                entity.setNombre(dto.getNombre());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el nombre de la categoría");
            }
        }

        if (dto.getDescripcion() != null) {
            if (permissions.contains("update_categoria_descripcion")) {
                entity.setDescripcion(dto.getDescripcion());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la descripción de la categoría");
            }
        }

        return entity;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.filter;

import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.service.RolService;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.security.CurrentUser;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class UsuarioFilter extends BaseFilter<Usuario, UsuarioDTO> {

    private final RolService rolService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(UsuarioFilter.class);

    public UsuarioFilter(RolService rolService, PasswordEncoder passwordEncoder) {
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDTO filterDTO(UsuarioDTO dto) {

        Set<String> permissions = CurrentUser.getPermissions();

        if (!permissions.contains("view_usuario_id")) {
            dto.setId(null);
        }
        if (!permissions.contains("view_usuario_rol_id")) {
            dto.setRolId(null);
        }
        if (!permissions.contains("view_usuario_nombre")) {
            dto.setNombre(null);
        }
        if (!permissions.contains("view_usuario_email_personal")) {
            dto.setEmailPersonal(null);
        }
        if (!permissions.contains("view_usuario_email_corporativo")) {
            dto.setEmailCorporativo(null);
        }
        if (!permissions.contains("view_usuario_contrasena")) {
            dto.setContrasena(null);
        }
        if (!permissions.contains("view_usuario_departamento")) {
            dto.setDepartamento(null);
        }

        return dto;
    }

    @Override
    public Usuario filterEntityToSave(Usuario entity) throws InsufficientSavingPermissionsException {

        Set<String> permissions = CurrentUser.getPermissions();

        entity.setId(null);

        if (!permissions.contains("update_usuario_rol_id")
                || !permissions.contains("update_usuario_nombre")
                || !permissions.contains("update_usuario_email_personal")
                || !permissions.contains("update_usuario_email_corporativo")
                || !permissions.contains("update_usuario_contrasena")
                || !permissions.contains("update_usuario_departamento")) {
            throw new InsufficientSavingPermissionsException(String.format("El usuario no tiene permisos para guardar la entidad %s", Usuario.class.toString()));
        }

        entity.setContrasena(passwordEncoder.encode(entity.getContrasena()));

        return entity;

    }

    @Override
    public Usuario filterEntityToUpdate(Usuario entity, UsuarioDTO dto) throws InsufficientSavingPermissionsException {

        Set<String> permissions = CurrentUser.getPermissions();

        log.info(permissions.toString());

        if (dto.getRolId() != null) {
            if (permissions.contains("update_usuario_rol_id")) {
                entity.setRolId(dto.getRolId());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el rol del usuario");
            }
        }

        if (dto.getNombre() != null) {
            if (permissions.contains("update_usuario_nombre")) {
                entity.setNombre(dto.getNombre());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el nombre del usuario");
            }
        }

        if (dto.getEmailPersonal() != null) {
            if (permissions.contains("update_usuario_email_personal")) {
                entity.setEmailPersonal(dto.getEmailPersonal());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el correo personal del usuario");
            }
        }

        if (dto.getEmailCorporativo() != null) {
            if (permissions.contains("update_usuario_email_corporativo")) {
                entity.setEmailCorporativo(dto.getEmailCorporativo());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el correo corporativo del usuario");
            }
        }

        if (dto.getContrasena() != null) {
            if (permissions.contains("update_usuario_contrasena")) {
                entity.setContrasena(passwordEncoder.encode(dto.getContrasena()));
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar la contraseña del usuario");
            }
        }

        if (dto.getDepartamento() != null) {
            if (permissions.contains("update_usuario_departamento")) {
                entity.setDepartamento(dto.getDepartamento());
            } else {
                throw new InsufficientSavingPermissionsException("No tiene permisos para actualizar el departamento del usuario");
            }
        }

        return entity;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.util.mapper;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.service.RolService;
import com.jsj.api.util.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class UsuarioMapper implements BaseMapper<Usuario, UsuarioDTO> {

    private final RolService rolService;

    public UsuarioMapper(RolService rolService) {
        this.rolService = rolService;
    }
    
    @Override
    public UsuarioDTO toDTO(Usuario entity, Set<String> permissions) {
        
        UsuarioDTO dto = new UsuarioDTO();
        
        if (permissions.contains("view_usuario_id") && entity.getId() != null) {dto.setId(entity.getId());}
        if (permissions.contains("view_usuario_rol_id") && entity.getRol() != null) {dto.setRolId(entity.getRol().getId());}
        if (permissions.contains("view_usuario_nombre") && entity.getNombre() != null) {dto.setNombre(entity.getNombre());}
        if (permissions.contains("view_usuario_email_personal") && entity.getEmailPersonal() != null) {dto.setEmailPersonal(entity.getEmailPersonal());}
        if (permissions.contains("view_usuario_email_corporativo") && entity.getEmailCorporativo() != null) {dto.setEmailCorporativo(entity.getEmailCorporativo());}
        if (permissions.contains("view_usuario_contrasena") && entity.getContrasena() != null) {dto.setContrasena(entity.getContrasena());}
        if (permissions.contains("view_usuario_departamento") && entity.getDepartamento() != null) {dto.setDepartamento(entity.getDepartamento());}
        
        return dto;
        
    }

    @Override
    public Usuario toEntity(UsuarioDTO dto, Set<String> permissions) {
        
        Usuario entity = new Usuario();
        
        if (permissions.contains("update_usuario_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_usuario_rol_id") && dto.getRolId() != null) {
            Rol rol = rolService.findById(dto.getRolId())
                    .orElseThrow(() -> new IllegalArgumentException("Rol not found"));
            entity.setRol(rol);
        }
        if (permissions.contains("update_usuario_nombre") && dto.getNombre() != null) {entity.setNombre(dto.getNombre());}
        if (permissions.contains("update_usuario_email_personal") && dto.getEmailPersonal() != null) {entity.setEmailPersonal(dto.getEmailPersonal());}
        if (permissions.contains("update_usuario_email_corporativo") && dto.getEmailCorporativo() != null) {entity.setEmailCorporativo(dto.getEmailCorporativo());}
        if (permissions.contains("update_usuario_contrasena") && dto.getContrasena() != null) {entity.setContrasena(dto.getContrasena());}
        if (permissions.contains("update_usuario_departamento") && dto.getDepartamento() != null) {entity.setDepartamento(dto.getDepartamento());}
 
        return entity;
        
    }

    @Override
    public void updateEntityFromDTO(UsuarioDTO dto, Usuario entity, Set<String> permissions) {
        
        if (permissions.contains("update_usuario_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_usuario_rol_id") && dto.getRolId() != null) {
            Rol rol = rolService.findById(dto.getRolId())
                    .orElseThrow(() -> new IllegalArgumentException("Rol not found"));
            entity.setRol(rol);
        }
        if (permissions.contains("update_usuario_nombre") && dto.getNombre() != null) {entity.setNombre(dto.getNombre());}
        if (permissions.contains("update_usuario_email_personal") && dto.getEmailPersonal() != null) {entity.setEmailPersonal(dto.getEmailPersonal());}
        if (permissions.contains("update_usuario_email_corporativo") && dto.getEmailCorporativo() != null) {entity.setEmailCorporativo(dto.getEmailCorporativo());}
        if (permissions.contains("update_usuario_contrasena") && dto.getContrasena() != null) {entity.setContrasena(dto.getContrasena());}
        if (permissions.contains("update_usuario_departamento") && dto.getDepartamento() != null) {entity.setDepartamento(dto.getDepartamento());}
    
    }
}

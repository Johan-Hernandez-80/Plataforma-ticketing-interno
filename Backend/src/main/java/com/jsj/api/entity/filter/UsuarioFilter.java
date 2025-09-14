/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.service.RolService;
import com.jsj.api.entity.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class UsuarioFilter implements BaseFilter<Usuario, UsuarioDTO> {

    private final RolService rolService;

    public UsuarioFilter(RolService rolService) {
        this.rolService = rolService;
    }
    
    @Override
    public UsuarioDTO filterDTO(UsuarioDTO dto, Set<String> permissions) {
        
        if (!permissions.contains("view_usuario_id")) {dto.setId(null);}
        if (!permissions.contains("view_usuario_rol_id")) {dto.setRolId(null);}
        if (!permissions.contains("view_usuario_nombre")) {dto.setNombre(null);}
        if (!permissions.contains("view_usuario_email_personal")) {dto.setEmailPersonal(null);}
        if (!permissions.contains("view_usuario_email_corporativo")) {dto.setEmailCorporativo(null);}
        if (!permissions.contains("view_usuario_contrasena")) {dto.setContrasena(null);}
        if (!permissions.contains("view_usuario_departamento")) {dto.setDepartamento(null);}
        
        return dto;
        
    }

    @Override
    public Usuario filterEntity(Usuario entity, Set<String> permissions) {
        
        if (!permissions.contains("update_usuario_id")) {entity.setId(null);}
        if (!permissions.contains("update_usuario_rol_id")) {entity.setRol(null);}
        if (!permissions.contains("update_usuario_nombre")) {entity.setNombre(null);}
        if (!permissions.contains("update_usuario_email_personal")) {entity.setEmailPersonal(null);}
        if (!permissions.contains("update_usuario_email_corporativo")) {entity.setEmailCorporativo(null);}
        if (!permissions.contains("update_usuario_contrasena")) {entity.setContrasena(null);}
        if (!permissions.contains("update_usuario_departamento")) {entity.setDepartamento(null);}
 
        return entity;
        
    }

}

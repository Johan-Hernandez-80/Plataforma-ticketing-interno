/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.security;

import com.jsj.api.service.UsuarioService;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class PermissionService {
    
    private final UsuarioService usuarioService;

    public PermissionService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Set<String> getPermissionsForUser(Long userId) {
        return usuarioService.getPermissionsById(userId);
    }
    
}

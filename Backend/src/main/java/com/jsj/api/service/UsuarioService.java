/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.util.BaseService;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioDAO> {
    
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repo, UsuarioDAO dao, PasswordEncoder passwordEncoder) {
        super(repo, Usuario.class, dao);
        this.passwordEncoder = passwordEncoder;
    }

    public Set<String> getPermissionsById(Long userId) {
        return dao.getPermissionsById(userId);
    }

    public Usuario validateCredentials(String email, String password) {
        Usuario user = dao.findByEmailPersonal(email);
        if (user == null) {
            user = dao.findByEmailCorporativo(email);
        }
        if (user != null && passwordEncoder.matches(password, user.getContrasena())) {
            return user;
        }
        return null;
    }

}

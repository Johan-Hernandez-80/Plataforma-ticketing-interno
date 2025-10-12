/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.entity.dao.RolDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.NotificacionDTO;
import com.jsj.api.entity.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioDTO, UsuarioDAO> {
    
    private final PasswordEncoder passwordEncoder;
    private final NotificacionDAO notificacionDAO;
    private final RolDAO rolDAO;

    public UsuarioService(PasswordEncoder passwordEncoder, NotificacionDAO notificacionDAO, RolDAO rolDAO, UsuarioDAO dao) {
        super(dao);
        this.passwordEncoder = passwordEncoder;
        this.notificacionDAO = notificacionDAO;
        this.rolDAO = rolDAO;
    }

    public Set<String> getPermissionsById(Long userId) {
        return dao.getPermissionsById(userId);
    }
    
    public Optional<Usuario> findById(long id) {
        return dao.findById(id);
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
    
    public List<NotificacionDTO> getNotificacionesById(Long idUsuario) {
        if (dao.existsById(idUsuario)) {
            return null;
        }
        return notificacionDAO.getNotificacionesByIdUsuario(idUsuario);
    }

    public UsuarioDTO updateUsuario(Long idUsuario, UsuarioDTO usuarioDTO) {
        return dao.updateUsuario(idUsuario, usuarioDTO);
    }

    public UsuarioDTO save(UsuarioDTO dto) {
        Rol rol = null;
        if (rolDAO.isIdRolAnAgente(dto.getRolId())) {
            rol = rolDAO.getRolAgente();
        } else {
            rol = rolDAO.getRolUsuario();
        }
        
        return dao.save(dto, rol);
    }

    
}

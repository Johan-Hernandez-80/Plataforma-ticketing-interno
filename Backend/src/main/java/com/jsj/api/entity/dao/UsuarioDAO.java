/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.entity.filter.BaseFilter;
import com.jsj.api.entity.filter.UsuarioFilter;
import com.jsj.api.entity.mapper.BaseMapper;
import com.jsj.api.entity.mapper.UsuarioMapper;
import com.jsj.api.repository.UsuarioRepository;
import com.jsj.api.security.CurrentUser;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class UsuarioDAO extends BaseDAO<Usuario, Long, UsuarioDTO, UsuarioMapper, UsuarioFilter, UsuarioRepository> {

    public UsuarioDAO(UsuarioMapper mapper, UsuarioFilter filter, UsuarioRepository repo) {
        super(mapper, filter, repo);
    }

    public Set<String> getPermissionsById(Long userId) {
        return repo.getPermissionsById(userId);
    }

    public Usuario findByEmailPersonal(String email) {
        return repo.findByEmailPersonal(email);
    }

    public Usuario findByEmailCorporativo(String email) {
        return repo.findByEmailCorporativo(email);
    }

    public boolean existsById(Long idUsuario) {
        return repo.existsById(idUsuario);
    }

    public UsuarioDTO updateUsuario(Long idUsuario, UsuarioDTO dto) {
        Optional<Usuario> opt = repo.findById(idUsuario);
        if (opt.isEmpty()) {
            return null;
        }
        Usuario entity = opt.get();
        
        mapper.updateEntityFromDTO(dto, entity);
        
        filter.filterEntity(entity);
        
        return filter.filterDTO(mapper.toDTO(repo.save(entity)));
    }
    
    public UsuarioDTO save(UsuarioDTO dto, Rol rol) {
        Usuario entity = mapper.toEntity(dto);
        entity.setRol(rol);
        
        filter.filterEntity(entity);
        
        return filter.filterDTO(mapper.toDTO(repo.save(entity)));
    }

    public boolean isAdmin(Long currentUserId) {
        return repo.isAdmin(currentUserId);
    }

    public boolean isAgente(Long id) {
        return repo.isAgente(id);
    }

    public boolean isAgenteAssignedToTicket(Long idUsuario, Long idTicket) {
        return repo.isAgenteAssignedToTicket(idUsuario, idTicket);
    }

    public boolean isTicketBelongsToUsuario(Long idUsuario, Long idTicket) {
        return repo.isTicketBelongsToUsuario(idUsuario, idTicket);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.dao.RolDAO;
import com.jsj.api.repository.RolRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class RolService {

    private final RolDAO dao;
    
    public RolService(RolDAO dao) {
        this.dao = dao;
    }

    public Rol getRolUsuario() {
        return dao.getRolUsuario();
    }

    public Rol getRolAgente() {
        return dao.getRolAgente();
    }

    public Optional<String> findRolNombreById(Long rolId) {
        return dao.findRolNombreById(rolId);
    }

}

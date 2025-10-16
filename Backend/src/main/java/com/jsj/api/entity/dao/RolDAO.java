/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Rol;
import com.jsj.api.repository.RolRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class RolDAO {

    private final RolRepository repo;

    public RolDAO(RolRepository repo) {
        this.repo = repo;
    }

    public Rol getRolUsuario() {
        return repo.getRolUsuario();
    }

    public Rol getRolAgente() {
        return repo.getRolAgente();
    }

    public boolean isIdRolAnAgente(Long rolId) {
        return ((RolRepository) repo).existsByIdAndNombre(rolId, "agente");
    }

    public Optional<String> findRolNombreById(Long rolId) {
        return repo.findById(rolId)
                .map(Rol::getNombre);
    }

    public boolean existsById(Long rolId) {
        return repo.existsById(rolId);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.repository;

import com.jsj.api.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Juan José Molano Franco
 */
public interface RolRepository extends JpaRepository<Rol, Long> {

    public boolean existsByIdAndNombre(Long id, String nombre);
    
    @Query("SELECT r FROM Rol r WHERE r.nombre = 'usuario'")
    Rol getRolUsuario();
    
    @Query("SELECT r FROM Rol r WHERE r.nombre = 'agente'")
    Rol getRolAgente();
    
}

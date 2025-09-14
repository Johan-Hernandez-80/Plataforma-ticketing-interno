/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public interface RolDAO extends JpaRepository<Rol, Long> {

    @Query("SELECT r FROM Rol r WHERE r.nombre = 'usuario'")
    Rol getRolUsuario();
    
    @Query("SELECT r FROM Rol r WHERE r.nombre = 'agente'")
    Rol getRolAgente();

}

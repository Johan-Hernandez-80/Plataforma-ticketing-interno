/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jsj.api.repository;

import com.jsj.api.entity.Usuario;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Juan José Molano Franco
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT p.nombre FROM Usuario u "
            + "JOIN u.rol r "
            + "JOIN r.permisos p "
            + "WHERE u.id = :userId")
    Set<String> getPermissionsById(@Param("userId") Long userId);

    @Query("SELECT u FROM Usuario u WHERE u.emailPersonal = :emailPersonal")
    Usuario findByEmailPersonal(@Param("emailPersonal") String emailPersonal);

    @Query("SELECT u FROM Usuario u WHERE u.emailCorporativo = :emailCorporativo")
    Usuario findByEmailCorporativo(@Param("emailCorporativo") String emailCorporativo);

}

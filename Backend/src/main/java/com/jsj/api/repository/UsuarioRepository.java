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

    @Query("""
            SELECT p.nombre FROM Permiso p
            JOIN Rol r ON p MEMBER OF r.permisos
            JOIN Usuario u ON u.rolId = r.id
            WHERE u.id = :userId
        """)
    Set<String> getPermissionsById(@Param("userId") Long userId);

    @Query("SELECT u FROM Usuario u WHERE u.emailPersonal = :emailPersonal")
    Usuario findByEmailPersonal(@Param("emailPersonal") String emailPersonal);

    @Query("SELECT u FROM Usuario u WHERE u.emailCorporativo = :emailCorporativo")
    Usuario findByEmailCorporativo(@Param("emailCorporativo") String emailCorporativo);

    @Query("""
            SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END
            FROM Usuario u, Rol r
            WHERE u.rolId = r.id AND u.id = :usuarioId AND r.nombre = 'admin'
        """)
    boolean isAdmin(@Param("usuarioId") Long usuarioId);

    @Query("""
            SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END
            FROM Usuario u, Rol r
            WHERE u.rolId = r.id AND u.id = :usuarioId AND r.nombre = 'agente'
        """)
    boolean isAgente(@Param("usuarioId") Long usuarioId);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END "
            + "FROM Asignacion a "
            + "WHERE a.agente.id = :agenteId AND a.ticket.id = :ticketId")
    boolean isAgenteAssignedToTicket(@Param("agenteId") Long agenteId,
            @Param("ticketId") Long ticketId);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END "
            + "FROM Ticket t "
            + "WHERE t.usuario.id = :usuarioId AND t.id = :ticketId")
    boolean isTicketBelongsToUsuario(@Param("usuarioId") Long usuarioId,
            @Param("ticketId") Long ticketId);
    
    boolean existsByEmailPersonal(String emailPersonal);
    
    boolean existsByEmailCorporativo(String emailCorporativo);

}

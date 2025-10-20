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

    @Query(
            value = "SELECT p.nombre "
            + "FROM permisos p "
            + "JOIN permisos_roles pr ON pr.permiso_id = p.id "
            + "JOIN roles r ON r.id = pr.rol_id "
            + "JOIN usuarios u ON u.rol_id = r.id "
            + "WHERE u.id = :userId",
            nativeQuery = true
    )
    Set<String> getPermissionsById(@Param("userId") Long userId);

    Usuario findByEmailPersonal(String emailPersonal);

    Usuario findByEmailCorporativo(String emailCorporativo);

    @Query(
            value = """
        SELECT CASE WHEN COUNT(u.id) > 0 THEN true ELSE false END
        FROM usuarios u
        JOIN roles r ON u.rol_id = r.id
        WHERE u.id = :usuarioId AND r.nombre = 'admin'
    """,
            nativeQuery = true
    )
    int isAdmin(@Param("usuarioId") Long usuarioId);

    @Query(
            value = """
        SELECT CASE WHEN COUNT(u.id) > 0 THEN true ELSE false END
        FROM usuarios u
        JOIN roles r ON u.rol_id = r.id
        WHERE u.id = :usuarioId AND r.nombre = 'agente'
    """,
            nativeQuery = true
    )
    int isAgente(@Param("usuarioId") Long usuarioId);

    @Query("""
    SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END
    FROM Ticket t
    WHERE t.agenteId = :agenteId AND t.id = :ticketId
""")
    boolean isAgenteAssignedToTicket(@Param("agenteId") Long agenteId,
            @Param("ticketId") Long ticketId);

    @Query("""
        SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END
        FROM Ticket t
        WHERE t.usuarioId = :usuarioId AND t.id = :ticketId
    """)
    boolean isTicketBelongsToUsuario(@Param("usuarioId") Long usuarioId,
            @Param("ticketId") Long ticketId);

    boolean existsByEmailPersonal(String emailPersonal);

    boolean existsByEmailCorporativo(String emailCorporativo);

    @Query(value = "SELECT COUNT(*) FROM usuarios u JOIN roles r ON u.rol_id = r.id WHERE r.nombre = 'usuario'", nativeQuery = true)
    long countEmpleadosActivos();

    @Query(value = "SELECT COUNT(*) FROM usuarios u JOIN roles r ON u.rol_id = r.id WHERE r.nombre = 'agente'", nativeQuery = true)
    long countAgentesActivos();

}

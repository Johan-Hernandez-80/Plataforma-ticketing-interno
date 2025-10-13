/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.security.CurrentUser;
import java.util.Set;

/**
 *
 * @author Juan José Molano Franco
 */
public abstract class BaseFilter <
        E, // Entidad
        DTO // Para la mostrar información sin recursión (listas de objetos que tienen a la entidad)
        > {

    protected final Set<String> permissions = CurrentUser.getPermissions();
    
    // Filtra data de la entidad
    // Esta entidad se usa para ALTERAR DATA en la DB, implemente los permisos de rol apropiados
    abstract E filterEntity(E entity);

    // Filtra data del DTO
    // Este DTO se usa para EXPONER DATA en la API, implemente los permisos de rol apropiados
    abstract DTO filterDTO(DTO dto);
    
}

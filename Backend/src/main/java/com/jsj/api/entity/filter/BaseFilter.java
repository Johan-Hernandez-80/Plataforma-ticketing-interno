/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.exception.InsufficientSavingPermissionsException;
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

    // Filtra data de la entidad nueva a guardar
    // Esta entidad se usa para ALTERAR DATA en la DB, implemente los permisos de rol apropiados, nulificación de datos autogenerados y encriptación de contrasenas
    abstract E filterEntityToSave(E entity) throws InsufficientSavingPermissionsException;
    
    // Filtra data de la entidad nueva a guardar
    // Esta entidad se usa para ALTERAR DATA en la DB, implemente los permisos de rol apropiados, skip de datos nulos (sin cambios) y encriptación de contrasenas
    abstract E filterEntityToUpdate(E entity, DTO dto) throws InsufficientSavingPermissionsException;

    // Filtra data del DTO
    // Este DTO se usa para EXPONER DATA en la API, implemente los permisos de rol apropiados
    abstract DTO filterDTO(DTO dto);
    
}

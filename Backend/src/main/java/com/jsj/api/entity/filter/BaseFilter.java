/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import java.util.Set;

/**
 *
 * @author Juan José Molano Franco
 */
public interface BaseFilter <
        E, // Entity
        DTO // For handling info without recursion
        > {
    
    // Filter entity data
    // This entity is used to ALTER DATA in the DB, implement appropiate ROLE PERMISSIONS
    E filterEntity(E entity, Set<String> permissions);

    // Filter DTO data
    // This DTO is used to EXPOSE DATA in the API, implement appropiate ROLE PERMISSIONS
    DTO filterDTO(DTO dto, Set<String> permissions);
    
}

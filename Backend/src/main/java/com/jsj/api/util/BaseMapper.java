/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.util;

import java.util.Set;
import org.mapstruct.MappingTarget;

/**
 *
 * @author Juan José Molano Franco
 */
public interface BaseMapper<
        E, // Entity
        DTO // For handling info without recursion
        > {

    // Convert entity to response DTO
    // This is used to EXPOSE DATA in the API, implement appropiate ROLE PERMISSIONS
    DTO toDTO(E entity, Set<String> permissions);

    // Convert create DTO to entity
    // This is used to ALTER DATA in the DB, implement appropiate ROLE PERMISSIONS
    E toEntity(DTO dto, Set<String> permissions);

    // Apply an update DTO to an existing entity (partial update)
    // This is used to ALTER DATA in the DB, implement appropiate ROLE PERMISSIONS
    void updateEntityFromDTO(DTO dto, E entity, Set<String> permissions);

}

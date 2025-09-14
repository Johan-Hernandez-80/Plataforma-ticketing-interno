/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.mapper;

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

    // To recieve data
    DTO toDTO(E entity);

    // To send back data
    E toEntity(DTO dto);

    // Also to send back data
    void updateEntityFromDTO(DTO dto, E entity);

}

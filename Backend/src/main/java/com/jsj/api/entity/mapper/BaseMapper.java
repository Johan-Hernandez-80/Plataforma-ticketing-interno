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
        E, // Entidad
        DTO // Para manejar información sin recursión
        > {

    // Para recibir data
    DTO toDTO(E entity);

    // Para retornar data
    E toEntity(DTO dto);

    // También para retornar data
    // Si se crea con maptstruct hará una alteración automática a los campos no nulos
    void updateEntityFromDTO(DTO dto, E entity);

}

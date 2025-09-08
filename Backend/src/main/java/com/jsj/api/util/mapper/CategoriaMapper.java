/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.util.mapper;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.util.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class CategoriaMapper implements BaseMapper<Categoria, CategoriaDTO> {

    @Override
    public CategoriaDTO toDTO(Categoria entity, Set<String> permissions) {
        
        CategoriaDTO dto = new CategoriaDTO();
        
        if (permissions.contains("view_categoria_id") && entity.getId() != null) {dto.setId(entity.getId());}
        if (permissions.contains("view_categoria_nombre") && entity.getNombre() != null) {dto.setNombre(entity.getNombre());}
        if (permissions.contains("view_categoria_descripcion") && entity.getDescripcion() != null) {dto.setDescripcion(entity.getDescripcion());}

        return dto;
        
    }

    @Override
    public Categoria toEntity(CategoriaDTO dto, Set<String> permissions) {

        Categoria entity = new Categoria();
        
        if (permissions.contains("update_categoria_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_categoria_nombre") && dto.getNombre() != null) {entity.setNombre(dto.getNombre());}
        if (permissions.contains("update_categoria_descripcion") && dto.getDescripcion() != null) {entity.setDescripcion(dto.getDescripcion());}
        
        return entity;
        
    }

    @Override
    public void updateEntityFromDTO(CategoriaDTO dto, Categoria entity, Set<String> permissions) {

        if (permissions.contains("update_categoria_id") && dto.getId() != null) {entity.setId(dto.getId());}
        if (permissions.contains("update_categoria_nombre") && dto.getNombre() != null) {entity.setNombre(dto.getNombre());}
        if (permissions.contains("update_categoria_descripcion") && dto.getDescripcion() != null) {entity.setDescripcion(dto.getDescripcion());}
        
    }
    
}

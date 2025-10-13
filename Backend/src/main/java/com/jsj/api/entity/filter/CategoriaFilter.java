/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.service.TicketService;
import com.jsj.api.service.UsuarioService;
import com.jsj.api.entity.mapper.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class CategoriaFilter extends BaseFilter<Categoria, CategoriaDTO> {

    @Override
    public CategoriaDTO filterDTO(CategoriaDTO dto) {
        
        if (!permissions.contains("view_categoria_id")) { dto.setId(null); }
        if (!permissions.contains("view_categoria_nombre")) { dto.setNombre(null); }
        if (!permissions.contains("view_categoria_descripcion")) { dto.setDescripcion(null); }
        
        return dto;
    }

    @Override
    public Categoria filterEntity(Categoria entity) {
        
        if (!permissions.contains("update_categoria_id")) { entity.setId(null); }
        if (!permissions.contains("update_categoria_nombre")) { entity.setNombre(null); }
        if (!permissions.contains("update_categoria_descripcion")) { entity.setDescripcion(null); }
        
        return entity;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.mapper;

import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Juan José Molano Franco
 */
@Mapper(config = BaseMapperConfig.class)
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {

    @Override
    UsuarioDTO toDTO(Usuario entity);

    @Override
    Usuario toEntity(UsuarioDTO dto);

    @Override
    void updateEntityFromDTO(UsuarioDTO dto, @MappingTarget Usuario entity);

}

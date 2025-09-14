/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.mapper;

import com.jsj.api.entity.Categoria;
import com.jsj.api.entity.dto.CategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Juan José Molano Franco
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDTO> {

    @Override
    public CategoriaDTO toDTO(Categoria entity);

    @Override
    public Categoria toEntity(CategoriaDTO dto);

    @Override
    public void updateEntityFromDTO(CategoriaDTO dto, @MappingTarget Categoria entity);

}

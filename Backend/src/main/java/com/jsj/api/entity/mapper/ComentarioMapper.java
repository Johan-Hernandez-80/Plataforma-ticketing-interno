/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.mapper;

import com.jsj.api.entity.Comentario;
import com.jsj.api.entity.dto.ComentarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Juan José Molano Franco
 */
@Mapper(config = BaseMapperConfig.class)
public interface ComentarioMapper extends BaseMapper<Comentario, ComentarioDTO> {

    @Override
    @Mapping(source = "ticket.id", target = "ticketId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    public ComentarioDTO toDTO(Comentario entity);

    @Override
    public Comentario toEntity(ComentarioDTO dto);

    @Override
    public void updateEntityFromDTO(ComentarioDTO dto, @MappingTarget Comentario entity);

}

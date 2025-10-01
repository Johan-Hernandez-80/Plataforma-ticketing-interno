/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.mapper;

import com.jsj.api.entity.Asignacion;
import com.jsj.api.entity.dto.AsignacionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Juan José Molano Franco
 */
@Mapper(config = BaseMapperConfig.class)
public interface AsignacionMapper extends BaseMapper<Asignacion, AsignacionDTO> {

    @Override
    @Mapping(source = "ticket.id", target = "ticketId")
    @Mapping(source = "agente.id", target = "agenteId")
    public AsignacionDTO toDTO(Asignacion entity);

    @Override
    public Asignacion toEntity(AsignacionDTO dto);

    @Override
    void updateEntityFromDTO(AsignacionDTO dto, @MappingTarget Asignacion entity);

}

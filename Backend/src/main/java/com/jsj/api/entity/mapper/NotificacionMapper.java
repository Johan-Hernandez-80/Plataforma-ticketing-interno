/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.mapper;

import com.jsj.api.entity.Notificacion;
import com.jsj.api.entity.dto.NotificacionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Juan José Molano Franco
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificacionMapper extends BaseMapper<Notificacion, NotificacionDTO> {

    @Override
    @Mapping(source = "usuario.id", target = "usuarioId")
    public NotificacionDTO toDTO(Notificacion entity);

    @Override
    public Notificacion toEntity(NotificacionDTO dto);

    @Override
    public void updateEntityFromDTO(NotificacionDTO dto, @MappingTarget Notificacion entity);

}

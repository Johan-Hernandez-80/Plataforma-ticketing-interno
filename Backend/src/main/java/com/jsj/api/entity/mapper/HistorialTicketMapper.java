/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.mapper;

import com.jsj.api.entity.HistorialTicket;
import com.jsj.api.entity.dto.HistorialTicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Juan José Molano Franco
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistorialTicketMapper extends BaseMapper<HistorialTicket, HistorialTicketDTO> {

    @Override
    @Mapping(source = "ticket.id", target = "ticketId")
    public HistorialTicketDTO toDTO(HistorialTicket entity);

    @Override
    public HistorialTicket toEntity(HistorialTicketDTO dto);

    @Override
    public void updateEntityFromDTO(HistorialTicketDTO dto, @MappingTarget HistorialTicket entity);

}

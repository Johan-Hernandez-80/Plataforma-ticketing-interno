/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.mapper;

import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Juan José Molano Franco
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper extends BaseMapper<Ticket, TicketDTO> {

    @Override
    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "categoria.id", target = "categoriaId")
    public TicketDTO toDTO(Ticket entity);

    @Override
    public Ticket toEntity(TicketDTO dto);

    @Override
    public void updateEntityFromDTO(TicketDTO dto, @MappingTarget Ticket entity);
    
}

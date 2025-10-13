/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import com.jsj.api.entity.mapper.BaseMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class AsignacionFilter extends BaseFilter<Asignacion, AsignacionDTO> {

    @Override
    public AsignacionDTO filterDTO(AsignacionDTO dto) {

        if (!permissions.contains("view_asignacion_id")) { dto.setId(null); }
        if (!permissions.contains("view_asignacion_ticket_id")) { dto.setTicketId(null); }
        if (!permissions.contains("view_asignacion_agente_id")) { dto.setAgenteId(null); }
        if (!permissions.contains("view_asignacion_fecha_creacion")) { dto.setFechaCreacion(null); }

        return dto;
    }

    @Override
    public Asignacion filterEntity(Asignacion entity) {

        if (!permissions.contains("update_asignacion_id")) { entity.setId(null); }
        if (!permissions.contains("update_asignacion_ticket_id")) { entity.setTicket(null); }
        if (!permissions.contains("update_asignacion_agente_id")) { entity.setAgente(null); }
        if (!permissions.contains("update_asignacion_fecha_creacion")) { entity.setFechaCreacion(null); }

        return entity;
    }
}

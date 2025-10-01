/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.entity.filter;

import com.jsj.api.entity.*;
import com.jsj.api.entity.dto.*;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class NotificacionFilter implements BaseFilter<Notificacion, NotificacionDTO> {

    @Override
    public NotificacionDTO filterDTO(NotificacionDTO dto, Set<String> permissions) {

        if (!permissions.contains("view_notificacion_id")) { dto.setId(null); }
        if (!permissions.contains("view_notificacion_usuario_id")) { dto.setUsuarioId(null); }
        if (!permissions.contains("view_notificacion_mensaje")) { dto.setMensaje(null); }
        if (!permissions.contains("view_notificacion_fecha_creacion")) { dto.setFechaCreacion(null); }

        return dto;
    }

    @Override
    public Notificacion filterEntity(Notificacion entity, Set<String> permissions) {

        if (!permissions.contains("update_notificacion_id")) { entity.setId(null); }
        if (!permissions.contains("update_notificacion_usuario_id")) { entity.setUsuario(null); }
        if (!permissions.contains("update_notificacion_mensaje")) { entity.setMensaje(null); }
        if (!permissions.contains("update_notificacion_fecha_creacion")) { entity.setFechaCreacion(null); }

        return entity;
    }
}

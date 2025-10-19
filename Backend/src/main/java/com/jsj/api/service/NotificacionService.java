/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Notificacion;
import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.entity.dto.NotificacionDTO;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.repository.NotificacionRepository;
import java.util.List;
import jdk.javadoc.doclet.Reporter;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class NotificacionService extends BaseService<Notificacion, Long, NotificacionDTO, NotificacionDAO> {

    public NotificacionService(NotificacionDAO dao) {
        super(dao);
    }

    void notifyComentario(Long idTicket, Long usuarioId, String tituloTicket) throws InsufficientSavingPermissionsException {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setUsuarioId(usuarioId);
        dto.setMensaje(String.format("Nuevo comentario añadido al tiquete con id %s, \"%s\"", idTicket, tituloTicket));
        dao.save(dto);
    }

    void notifyPrioridad(Long idTicket, Long usuarioId, String titulo, String prioridad) throws InsufficientSavingPermissionsException {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setUsuarioId(usuarioId);
        dto.setMensaje(String.format("El tiquete con id %s, \"%s\", ha cambiado a \"%s\"", idTicket, titulo, prioridad));
        dao.save(dto);
    }

    void notifyCierre(Long idTicket, Long usuarioId, String titulo) throws InsufficientSavingPermissionsException {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setUsuarioId(usuarioId);
        dto.setMensaje(String.format("El tiquete con id %s, \"%s\", ha sido cerrado", idTicket, titulo));
        dao.save(dto);
    }

    void notifyReasignacion(Long idTicket, Long usuarioId, String titulo, String nombre, String emailCorporativo) throws InsufficientSavingPermissionsException {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setUsuarioId(usuarioId);
        dto.setMensaje(String.format("El tiquete con id %s, \"%s\", ha sido reasignado al agente %s, email: %s", idTicket, titulo, nombre, emailCorporativo));
        dao.save(dto);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Notificacion;
import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.entity.dto.NotificacionDTO;
import java.util.List;
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

}

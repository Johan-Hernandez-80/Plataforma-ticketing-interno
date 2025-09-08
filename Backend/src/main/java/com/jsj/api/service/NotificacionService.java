/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.util.BaseService;
import com.jsj.api.entity.Notificacion;
import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class NotificacionService extends BaseService<Notificacion, Long, NotificacionDAO> {

    public NotificacionService(NotificacionRepository repo, NotificacionDAO dao) {
        super(repo, Notificacion.class, dao);
    }
}

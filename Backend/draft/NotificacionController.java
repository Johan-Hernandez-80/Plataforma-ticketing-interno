/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.*;
import com.jsj.api.service.*;
import com.jsj.api.util.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Juan José Molano Franco
 */
@RestController
@RequestMapping("/notificaciones")
@Tag(name = "Notificacion", description = "Notificacion endpoints")
public class NotificacionController extends BaseController<Notificacion, Long> {

    public NotificacionController(NotificacionService service) {
        super(service);
    }
    
}

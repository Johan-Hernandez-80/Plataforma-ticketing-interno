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
@RequestMapping("/historialTickets")
@Tag(name = "", description = " endpoints")
public class HistorialTicketController extends BaseController<HistorialTicket, Long> {

    public HistorialTicketController(HistorialTicketService service) {
        super(service);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.entity.mapper.TicketMapper;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.service.BaseService;
import com.jsj.api.entity.filter.TicketFilter;
import com.jsj.api.service.TicketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Juan José Molano Franco
 */
@RestController
@RequestMapping("/tickets")
@Tag(name = "Tickets", description = "Operations related to Ticket entities")
public class TicketController extends BaseController<Ticket, Long, TicketDTO>{

    public TicketController(TicketService service) {
        super(service);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.service.BaseService;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.entity.filter.BaseFilter;
import com.jsj.api.entity.mapper.BaseMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *
 * @author Juan José Molano Franco
 */
public abstract class BaseController<
        E, // Entidad
        ID, // Identificador entidad
        DTO // DTO
        > {

    private final BaseService service;

    public BaseController(BaseService service) {
        this.service = service;
    }

}

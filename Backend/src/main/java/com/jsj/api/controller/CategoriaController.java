/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.service.BaseService;
import com.jsj.api.entity.Categoria;
import com.jsj.api.entity.dao.CategoriaDAO;
import com.jsj.api.entity.dto.CategoriaDTO;
import com.jsj.api.entity.mapper.CategoriaMapper;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.entity.filter.CategoriaFilter;
import com.jsj.api.service.CategoriaService;
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
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Operations related to Categoria entities")
public class CategoriaController extends BaseController<Categoria, Long, CategoriaDTO> {

    public CategoriaController(CategoriaService service) {
        super(service);
    }

}

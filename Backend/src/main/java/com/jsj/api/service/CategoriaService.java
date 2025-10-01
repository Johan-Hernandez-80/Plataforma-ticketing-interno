/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Categoria;
import com.jsj.api.entity.dao.CategoriaDAO;
import com.jsj.api.entity.dto.CategoriaDTO;
import com.jsj.api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class CategoriaService extends BaseService<Categoria, Long, CategoriaDTO, CategoriaDAO> {

    public CategoriaService(CategoriaDAO dao) {
        super(dao);
    }
}

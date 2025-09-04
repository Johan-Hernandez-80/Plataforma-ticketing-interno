/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.util.BaseServiceImpl;
import com.jsj.api.entity.Categoria;
import com.jsj.api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class CategoriaService extends BaseServiceImpl<Categoria, Long> {

    public CategoriaService(CategoriaRepository repo) {
        super(repo);
    }
}

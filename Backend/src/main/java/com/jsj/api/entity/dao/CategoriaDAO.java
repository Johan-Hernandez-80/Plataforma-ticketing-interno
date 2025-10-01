/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Categoria;
import com.jsj.api.entity.dto.CategoriaDTO;
import com.jsj.api.entity.filter.CategoriaFilter;
import com.jsj.api.entity.mapper.CategoriaMapper;
import com.jsj.api.repository.CategoriaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class CategoriaDAO extends BaseDAO<Categoria, Long, CategoriaDTO, CategoriaMapper, CategoriaFilter, CategoriaRepository> {

    public CategoriaDAO(CategoriaMapper mapper, CategoriaFilter filter, CategoriaRepository repo) {
        super(mapper, filter, repo);
    }

}

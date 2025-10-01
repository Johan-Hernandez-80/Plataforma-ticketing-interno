/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Comentario;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.filter.ComentarioFilter;
import com.jsj.api.entity.mapper.ComentarioMapper;
import com.jsj.api.repository.ComentarioRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class ComentarioDAO extends BaseDAO<Comentario, Long, ComentarioDTO, ComentarioMapper, ComentarioFilter, ComentarioRepository> {

    public ComentarioDAO(ComentarioMapper mapper, ComentarioFilter filter, ComentarioRepository repo) {
        super(mapper, filter, repo);
    }

}

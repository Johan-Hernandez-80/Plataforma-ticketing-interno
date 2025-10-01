/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Comentario;
import com.jsj.api.entity.dao.ComentarioDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class ComentarioService extends BaseService<Comentario, Long, ComentarioDTO, ComentarioDAO> {

    public ComentarioService(ComentarioDAO dao) {
        super(dao);
    }

}

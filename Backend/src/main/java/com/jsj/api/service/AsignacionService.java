/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.util.BaseService;
import com.jsj.api.entity.Asignacion;
import com.jsj.api.repository.AsignacionRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class AsignacionService extends BaseService<Asignacion, Long> {

    public AsignacionService(AsignacionRepository repo) {
        super(repo);
    }
}

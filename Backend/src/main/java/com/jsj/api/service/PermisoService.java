/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Permiso;
import com.jsj.api.entity.dao.PermisoDAO;
import com.jsj.api.repository.PermisoRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class PermisoService extends BaseService<Permiso, Long, PermisoDAO> {

    public PermisoService(PermisoRepository repo, PermisoDAO dao) {
        super(repo, Permiso.class, dao);
    }
}

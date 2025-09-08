/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.dao.RolDAO;
import com.jsj.api.repository.RolRepository;
import com.jsj.api.util.BaseService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class RolService extends BaseService<Rol, Long, RolDAO> {

    public RolService(RolRepository repo, RolDAO dao) {
        super(repo, Rol.class, dao);
    }

}

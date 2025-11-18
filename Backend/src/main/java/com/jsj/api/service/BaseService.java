/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Notificacion;
import com.jsj.api.entity.dao.BaseDAO;
import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.FluentQuery;

import java.util.function.Function;

/**
 *
 * @author Juan José Molano Franco
 */
@Transactional
public abstract class BaseService<E, ID, DTO, DAO extends BaseDAO> {

    public final DAO dao;

    public BaseService(DAO dao) {
        this.dao = dao;
    }
    
}

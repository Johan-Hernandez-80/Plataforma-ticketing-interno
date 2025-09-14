/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

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
public abstract class BaseService<T, ID, DAO> {

    protected final JpaRepository<T, ID> repo;
    private final Class<T> entityClass;
    public final DAO dao;

    public BaseService(JpaRepository<T, ID> repo, Class<T> entityClass, DAO dao) {
        this.repo = repo;
        this.entityClass = entityClass;
        this.dao = dao;
    }

    public String getEntityName() {
        return entityClass.getSimpleName().toLowerCase();
    }

    // CRUD / list variants
     public <S extends T> S save(S entity) {
        return repo.save(entity);
    }

    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }

    public Optional<T> findById(ID id) {
        return repo.findById(id);
    }

    public boolean existsById(ID id) {
        return repo.existsById(id);
    }

    public long count() {
        return repo.count();
    }

    public List<T> findAll() {
        return repo.findAll();
    }

    public List<T> findAllById(Iterable<ID> ids) {
        return repo.findAllById(ids);
    }

    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    public void delete(T entity) {
        repo.delete(entity);
    }

    public void deleteAllById(Iterable<? extends ID> ids) {
        repo.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends T> entities) {
        repo.deleteAll(entities);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    // Sorting / paging
    public List<T> findAll(Sort sort) {
        return repo.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // JPA-specific    
    public void flush() {
        repo.flush();
    }

    public <S extends T> S saveAndFlush(S entity) {
        return repo.saveAndFlush(entity);
    }

    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return repo.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch() {
        repo.deleteAllInBatch();
    }

    public void deleteAllInBatch(Iterable<T> entities) {
        repo.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        repo.deleteAllByIdInBatch(ids);
    }

    public T getReferenceById(ID id) {
        return repo.getReferenceById(id);
    }

    // Query by Example    
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return repo.findOne(example);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return repo.findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return repo.findAll(example, sort);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return repo.findAll(example, pageable);
    }

    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> qf) {
        return repo.findBy(example, qf);
    }

    public <S extends T> long count(Example<S> example) {
        return repo.count(example);
    }

    public <S extends T> boolean exists(Example<S> example) {
        return repo.exists(example);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.util;

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
public class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    protected final JpaRepository<T, ID> repo;

    public BaseServiceImpl(JpaRepository<T, ID> repo) {
        this.repo = repo;
    }

    // CRUD / list variants
    @Override
    public <S extends T> S save(S entity) {
        return repo.save(entity);
    }
    
    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }
    
    @Override
    public Optional<T> findById(ID id) {
        return repo.findById(id);
    }
    
    @Override
    public boolean existsById(ID id) {
        return repo.existsById(id);
    }
    
    @Override
    public long count() {
        return repo.count();
    }
    
    @Override
    public List<T> findAll() {
        return repo.findAll();
    }
    
    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return repo.findAllById(ids);
    }
    
    @Override
    public void deleteById(ID id) {
        repo.deleteById(id);
    }
    
    @Override
    public void delete(T entity) {
        repo.delete(entity);
    }
    
    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        repo.deleteAllById(ids);
    }
    
    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        repo.deleteAll(entities);
    }
    
    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    // Sorting / paging
    @Override
    public List<T> findAll(Sort sort) {
        return repo.findAll(sort);
    }
    
    @Override
    public Page<T> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // JPA-specific    
    @Override
    public void flush() {
        repo.flush();
    }
    
    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return repo.saveAndFlush(entity);
    }
    
    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return repo.saveAllAndFlush(entities);
    }
    
    @Override
    public void deleteAllInBatch() {
        repo.deleteAllInBatch();
    }
    
    @Override
    public void deleteAllInBatch(Iterable<T> entities) {
        repo.deleteAllInBatch(entities);
    }
    
    @Override
    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        repo.deleteAllByIdInBatch(ids);
    }
    
    @Override
    public T getReferenceById(ID id) {
        return repo.getReferenceById(id);
    }

    // Query by Example    
    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return repo.findOne(example);
    }
    
    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return repo.findAll(example);
    }
    
    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return repo.findAll(example, sort);
    }
    
    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return repo.findAll(example, pageable);
    }
    
    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> qf) {
        return repo.findBy(example, qf);
    }
    
    @Override
    public <S extends T> long count(Example<S> example) {
        return repo.count(example);
    }
    
    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return repo.exists(example);
    }
}

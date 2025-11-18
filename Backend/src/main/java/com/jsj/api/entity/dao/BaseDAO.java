/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.filter.BaseFilter;
import com.jsj.api.entity.mapper.BaseMapper;
import com.jsj.api.security.CurrentUser;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

/**
 *
 * @author Juan José Molano Franco
 */
public class BaseDAO<
        E, ID, DTO,
        M extends BaseMapper<E, DTO>, // Mapper
        F extends BaseFilter<E, DTO>, // Filter
        R extends JpaRepository<E, ID> // Repository
        > {

    public final M mapper;
    public final F filter;
    public final R repo;

    public BaseDAO(M mapper, F filter, R repo) {
        this.mapper = mapper;
        this.filter = filter;
        this.repo = repo;
    }
    
    // CRUD / list variants
    public <S extends E> S save(S entity) {
        return repo.save(entity);
    }

    public <S extends E> List<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }

    public Optional<E> findById(ID id) {
        return repo.findById(id);
    }

    public boolean existsById(ID id) {
        return repo.existsById(id);
    }

    public long count() {
        return repo.count();
    }

    public List<E> findAll() {
        return repo.findAll();
    }

    public List<E> findAllById(Iterable<ID> ids) {
        return repo.findAllById(ids);
    }

    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    public void delete(E entity) {
        repo.delete(entity);
    }

    public void deleteAllById(Iterable<? extends ID> ids) {
        repo.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends E> entities) {
        repo.deleteAll(entities);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    // Sorting / paging
    public List<E> findAll(Sort sort) {
        return repo.findAll(sort);
    }

    public Page<E> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // JPA-specific    
    public void flush() {
        repo.flush();
    }

    public <S extends E> S saveAndFlush(S entity) {
        return repo.saveAndFlush(entity);
    }

    public <S extends E> List<S> saveAllAndFlush(Iterable<S> entities) {
        return repo.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch() {
        repo.deleteAllInBatch();
    }

    public void deleteAllInBatch(Iterable<E> entities) {
        repo.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        repo.deleteAllByIdInBatch(ids);
    }

    public E getReferenceById(ID id) {
        return repo.getReferenceById(id);
    }

    // Query by Example    
    public <S extends E> Optional<S> findOne(Example<S> example) {
        return repo.findOne(example);
    }

    public <S extends E> List<S> findAll(Example<S> example) {
        return repo.findAll(example);
    }

    public <S extends E> List<S> findAll(Example<S> example, Sort sort) {
        return repo.findAll(example, sort);
    }

    public <S extends E> Page<S> findAll(Example<S> example, Pageable pageable) {
        return repo.findAll(example, pageable);
    }

    public <S extends E, R2> R2 findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R2> qf) {
        return repo.findBy(example, qf);
    }

    public <S extends E> long count(Example<S> example) {
        return repo.count(example);
    }

    public <S extends E> boolean exists(Example<S> example) {
        return repo.exists(example);
    }
}

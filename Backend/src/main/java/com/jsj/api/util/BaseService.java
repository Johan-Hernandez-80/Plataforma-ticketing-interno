/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.util;

import org.springframework.data.domain.*;
import java.util.*;

import org.springframework.data.repository.query.FluentQuery;

import java.util.function.Function;

/**
 * Servicio que usa todos los métodos de JpaRepository
 * @author Juan José Molano Franco
 */
public interface BaseService<T, ID> {

    // CRUD / list variants
    <S extends T> S save(S entity);

    <S extends T> List<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    long count();

    List<T> findAll();

    List<T> findAllById(Iterable<ID> ids);

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(Iterable<? extends ID> ids);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

    // Sorting / paging
    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    // JPA-specific
    void flush();

    <S extends T> S saveAndFlush(S entity);

    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<T> entities);

    void deleteAllByIdInBatch(Iterable<ID> ids);

    T getReferenceById(ID id);

    // Query by Example
    <S extends T> Optional<S> findOne(Example<S> example);

    <S extends T> List<S> findAll(Example<S> example);

    <S extends T> List<S> findAll(Example<S> example, Sort sort);

    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends T, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
    
    <S extends T> long count(Example<S> example);

    <S extends T> boolean exists(Example<S> example);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.util;

import com.jsj.api.security.CurrentUser;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *
 * @author Juan José Molano Franco
 */
public abstract class BaseController<
        E, // Entity
        ID, // Unique identifier
        DTO, // DTO type
        DAO, // DAO type
        M extends BaseMapper<E, DTO> // Mapper type
        > {

    protected final BaseService<E, ID, DAO> service;
    protected final M mapper;

    public BaseController(BaseService<E, ID, DAO> service, M mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO dto) {
        Set<String> perms = CurrentUser.getPermissions();
        E entity = mapper.toEntity(dto, perms);
        E saved = service.save(entity);
        return ResponseEntity.ok(mapper.toDTO(saved, perms));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable ID id, @RequestBody DTO dto) {
        Set<String> perms = CurrentUser.getPermissions();
        return service.findById(id)
                .map(existing -> {
                    mapper.updateEntityFromDTO(dto, existing, perms);
                    E updated = service.save(existing);
                    return ResponseEntity.ok(mapper.toDTO(updated, perms));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getById(@PathVariable ID id) {
        Set<String> perms = CurrentUser.getPermissions();
        return service.findById(id)
                .map(e -> ResponseEntity.ok(mapper.toDTO(e, perms)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<DTO> getAll() {
        Set<String> perms = CurrentUser.getPermissions();
        return service.findAll().stream()
                .map(e -> mapper.toDTO(e, perms))
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        Set<String> perms = CurrentUser.getPermissions();
        String requiredPerm = "delete_" + service.getEntityName();

        if (!perms.contains(requiredPerm)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.repository;

import com.jsj.api.entity.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Categoria entities.
 */
@Repository // opcional
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Opcional: comprobar existencia por nombre (útil para validaciones)
    boolean existsByNombre(String nombre);

    // Opcional: búsqueda exacta por nombre
    Categoria findByNombre(String nombre);

    // Opcional: búsqueda por texto para autocompletar/filtrado
    List<Categoria> findByNombreContainingIgnoreCase(String fragment);
}

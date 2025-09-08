/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Categoria;
import com.jsj.api.entity.dao.CategoriaDAO;
import com.jsj.api.entity.dto.CategoriaDTO;
import com.jsj.api.util.*;
import com.jsj.api.util.mapper.CategoriaMapper;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Juan José Molano Franco
 */
@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Operations related to Categoria entities")
public class CategoriaController extends BaseController<Categoria, Long, CategoriaDTO, CategoriaDAO, CategoriaMapper> {

    public CategoriaController(BaseService<Categoria, Long, CategoriaDAO> service, CategoriaMapper mapper) {
        super(service, mapper);
    }

    @Operation(summary = "Create a new Categoria",
            description = "Creates a new Categoria entity with the given DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Categoria created successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = CategoriaDTO.class))),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PostMapping
    public ResponseEntity<CategoriaDTO> create(
            @Parameter(description = "DTO representing the Categoria to create", required = true)
            @RequestBody CategoriaDTO dto) {
        return super.create(dto);
    }

    @Operation(summary = "Update an existing Categoria",
            description = "Updates the Categoria identified by the given ID with the data from the DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Categoria updated successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = CategoriaDTO.class))),
                @ApiResponse(responseCode = "404", description = "Categoria not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(
            @Parameter(description = "ID of the Categoria to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "DTO containing updated Categoria information", required = true)
            @RequestBody CategoriaDTO dto) {
        return super.update(id, dto);
    }

    @Operation(summary = "Get a Categoria by ID",
            description = "Retrieves the Categoria identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Categoria retrieved successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = CategoriaDTO.class))),
                @ApiResponse(responseCode = "404", description = "Categoria not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getById(
            @Parameter(description = "ID of the Categoria to retrieve", required = true)
            @PathVariable Long id) {
        return super.getById(id);
    }

    @Operation(summary = "Get all Categorias",
            description = "Retrieves all Categoria entities.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of Categorias",
                        content = @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = CategoriaDTO.class))))
            })
    @GetMapping
    public List<CategoriaDTO> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Delete a Categoria",
            description = "Deletes the Categoria identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "204", description = "Categoria deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Categoria not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the Categoria to delete", required = true)
            @PathVariable Long id) {
        return super.delete(id);
    }
}

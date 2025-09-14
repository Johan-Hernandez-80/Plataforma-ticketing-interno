/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Comentario;
import com.jsj.api.entity.dao.ComentarioDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.mapper.ComentarioMapper;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jsj.api.entity.filter.ComentarioFilter;
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
@RequestMapping("/comentarios")
@Tag(name = "Comentarios", description = "Operations related to Comentario entities")
public class ComentarioController extends BaseController<Comentario, Long, ComentarioDTO, ComentarioDAO, ComentarioMapper, ComentarioFilter> {

    public ComentarioController(BaseService<Comentario, Long, ComentarioDAO> service, ComentarioMapper mapper, ComentarioFilter filter) {
        super(service, mapper, filter);
    }

    @Operation(summary = "Create a new Comentario",
            description = "Creates a new Comentario entity with the given DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Comentario created successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ComentarioDTO.class))),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PostMapping
    public ResponseEntity<ComentarioDTO> create(
            @Parameter(description = "DTO representing the Comentario to create", required = true)
            @RequestBody ComentarioDTO dto) {
        return super.create(dto);
    }

    @Operation(summary = "Update an existing Comentario",
            description = "Updates the Comentario identified by the given ID with the data from the DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Comentario updated successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ComentarioDTO.class))),
                @ApiResponse(responseCode = "404", description = "Comentario not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PatchMapping("/{id}")
    public ResponseEntity<ComentarioDTO> update(
            @Parameter(description = "ID of the Comentario to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "DTO containing updated Comentario information", required = true)
            @RequestBody ComentarioDTO dto) {
        if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return super.update(id, dto);
    }

    @Operation(summary = "Get a Comentario by ID",
            description = "Retrieves the Comentario identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Comentario retrieved successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ComentarioDTO.class))),
                @ApiResponse(responseCode = "404", description = "Comentario not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getById(
            @Parameter(description = "ID of the Comentario to retrieve", required = true)
            @PathVariable Long id) {
        return super.getById(id);
    }

    @Operation(summary = "Get all Comentarios",
            description = "Retrieves all Comentario entities.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of Comentarios",
                        content = @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = ComentarioDTO.class))))
            })
    @GetMapping
    public List<ComentarioDTO> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Delete a Comentario",
            description = "Deletes the Comentario identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "204", description = "Comentario deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Comentario not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the Comentario to delete", required = true)
            @PathVariable Long id) {
        if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return super.delete(id);
    }
}

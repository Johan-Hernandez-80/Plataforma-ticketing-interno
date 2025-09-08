/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Asignacion;
import com.jsj.api.entity.dao.AsignacionDAO;
import com.jsj.api.entity.dto.AsignacionDTO;
import com.jsj.api.util.BaseController;
import com.jsj.api.util.BaseMapper;
import com.jsj.api.util.BaseService;
import com.jsj.api.util.mapper.AsignacionMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jsj.api.util.*;
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
@RequestMapping("/asignaciones")
@Tag(name = "Asignaciones", description = "Operations related to Asignacion entities")
public class AsignacionController extends BaseController<Asignacion, Long, AsignacionDTO, AsignacionDAO, AsignacionMapper> {

    public AsignacionController(BaseService<Asignacion, Long, AsignacionDAO> service, AsignacionMapper mapper) {
        super(service, mapper);
    }

    @Operation(summary = "Create a new Asignacion",
            description = "Creates a new Asignacion entity with the given DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Asignacion created successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = AsignacionDTO.class))),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PostMapping
    public ResponseEntity<AsignacionDTO> create(
            @Parameter(description = "DTO representing the Asignacion to create", required = true)
            @RequestBody AsignacionDTO dto) {
        return super.create(dto);
    }

    @Operation(summary = "Update an existing Asignacion",
            description = "Updates the Asignacion identified by the given ID with the data from the DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Asignacion updated successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = AsignacionDTO.class))),
                @ApiResponse(responseCode = "404", description = "Asignacion not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PatchMapping("/{id}")
    public ResponseEntity<AsignacionDTO> update(
            @Parameter(description = "ID of the Asignacion to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "DTO containing updated Asignacion information", required = true)
            @RequestBody AsignacionDTO dto) {
        return super.update(id, dto);
    }

    @Operation(summary = "Get an Asignacion by ID",
            description = "Retrieves the Asignacion identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Asignacion retrieved successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = AsignacionDTO.class))),
                @ApiResponse(responseCode = "404", description = "Asignacion not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<AsignacionDTO> getById(
            @Parameter(description = "ID of the Asignacion to retrieve", required = true)
            @PathVariable Long id) {
        return super.getById(id);
    }

    @Operation(summary = "Get all Asignaciones",
            description = "Retrieves all Asignacion entities.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of Asignaciones",
                        content = @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = AsignacionDTO.class))))
            })
    @GetMapping
    public List<AsignacionDTO> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Delete an Asignacion",
            description = "Deletes the Asignacion identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "204", description = "Asignacion deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Asignacion not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the Asignacion to delete", required = true)
            @PathVariable Long id) {
        return super.delete(id);
    }
}

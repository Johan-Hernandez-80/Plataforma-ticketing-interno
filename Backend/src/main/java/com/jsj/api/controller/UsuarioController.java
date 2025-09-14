/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.service.BaseService;
import com.jsj.api.entity.*;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.*;
import com.jsj.api.entity.mapper.UsuarioMapper;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.entity.filter.UsuarioFilter;
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
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operations related to Usuario entities")
public class UsuarioController extends BaseController<Usuario, Long, UsuarioDTO, UsuarioDAO, UsuarioMapper, UsuarioFilter> {

    public UsuarioController(BaseService<Usuario, Long, UsuarioDAO> service, UsuarioMapper mapper, UsuarioFilter filter) {
        super(service, mapper, filter);
    }

    @Operation(summary = "Create a new Usuario",
            description = "Creates a new Usuario entity with the given DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Usuario created successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = UsuarioDTO.class))),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(
            @Parameter(description = "DTO representing the Usuario to create", required = true)
            @RequestBody UsuarioDTO dto) {
        if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return super.create(dto);
    }

    @Operation(summary = "Update an existing Usuario",
            description = "Updates the Usuario identified by the given ID with the data from the DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Usuario updated successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = UsuarioDTO.class))),
                @ApiResponse(responseCode = "404", description = "Usuario not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(
            @Parameter(description = "ID of the Usuario to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "DTO containing updated Usuario information", required = true)
            @RequestBody UsuarioDTO dto) {
        return super.update(id, dto);
    }

    @Operation(summary = "Get a Usuario by ID",
            description = "Retrieves the Usuario identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Usuario retrieved successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = UsuarioDTO.class))),
                @ApiResponse(responseCode = "404", description = "Usuario not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(
            @Parameter(description = "ID of the Usuario to retrieve", required = true)
            @PathVariable Long id) {
        return super.getById(id);
    }

    @Operation(summary = "Get all Usuarios",
            description = "Retrieves all Usuario entities.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of Usuarios",
                        content = @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class))))
            })
    @GetMapping
    public List<UsuarioDTO> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Delete a Usuario",
            description = "Deletes the Usuario identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "204", description = "Usuario deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Usuario not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the Usuario to delete", required = true)
            @PathVariable Long id) {
        if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return super.delete(id);
    }
}

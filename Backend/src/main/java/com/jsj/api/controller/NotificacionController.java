/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Notificacion;
import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.entity.dto.NotificacionDTO;
import com.jsj.api.util.BaseController;
import com.jsj.api.util.BaseService;
import com.jsj.api.util.mapper.NotificacionMapper;
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
@RequestMapping("/notificaciones")
@Tag(name = "Notificaciones", description = "Operations related to Notificacion entities")
public class NotificacionController extends BaseController<Notificacion, Long, NotificacionDTO, NotificacionDAO, NotificacionMapper> {

    public NotificacionController(BaseService<Notificacion, Long, NotificacionDAO> service, NotificacionMapper mapper) {
        super(service, mapper);
    }

    @Operation(summary = "Create a new Notificacion",
               description = "Creates a new Notificacion entity with the given DTO.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Notificacion created successfully",
                                content = @Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = NotificacionDTO.class))),
                   @ApiResponse(responseCode = "403", description = "Forbidden")
               })
    @PostMapping
    public ResponseEntity<NotificacionDTO> create(
            @Parameter(description = "DTO representing the Notificacion to create", required = true)
            @RequestBody NotificacionDTO dto) {
        return super.create(dto);
    }

    @Operation(summary = "Update an existing Notificacion",
               description = "Updates the Notificacion identified by the given ID with the data from the DTO.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Notificacion updated successfully",
                                content = @Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = NotificacionDTO.class))),
                   @ApiResponse(responseCode = "404", description = "Notificacion not found"),
                   @ApiResponse(responseCode = "403", description = "Forbidden")
               })
    @PatchMapping("/{id}")
    public ResponseEntity<NotificacionDTO> update(
            @Parameter(description = "ID of the Notificacion to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "DTO containing updated Notificacion information", required = true)
            @RequestBody NotificacionDTO dto) {
        return super.update(id, dto);
    }

    @Operation(summary = "Get a Notificacion by ID",
               description = "Retrieves the Notificacion identified by the given ID.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Notificacion retrieved successfully",
                                content = @Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = NotificacionDTO.class))),
                   @ApiResponse(responseCode = "404", description = "Notificacion not found")
               })
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionDTO> getById(
            @Parameter(description = "ID of the Notificacion to retrieve", required = true)
            @PathVariable Long id) {
        return super.getById(id);
    }

    @Operation(summary = "Get all Notificaciones",
               description = "Retrieves all Notificacion entities.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "List of Notificaciones",
                                content = @Content(mediaType = "application/json",
                                                   array = @ArraySchema(schema = @Schema(implementation = NotificacionDTO.class))))
               })
    @GetMapping
    public List<NotificacionDTO> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Delete a Notificacion",
               description = "Deletes the Notificacion identified by the given ID.",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Notificacion deleted successfully"),
                   @ApiResponse(responseCode = "404", description = "Notificacion not found"),
                   @ApiResponse(responseCode = "403", description = "Forbidden")
               })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the Notificacion to delete", required = true)
            @PathVariable Long id) {
        return super.delete(id);
    }
}

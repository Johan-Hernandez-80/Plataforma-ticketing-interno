/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.HistorialTicket;
import com.jsj.api.entity.dao.HistorialTicketDAO;
import com.jsj.api.entity.dto.HistorialTicketDTO;
import com.jsj.api.entity.mapper.HistorialTicketMapper;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.entity.mapper.BaseMapper;
import com.jsj.api.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jsj.api.entity.filter.HistorialTicketFilter;
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
@RequestMapping("/historialtickets")
@Tag(name = "HistorialTickets", description = "Operations related to HistorialTicket entities")
public class HistorialTicketController extends BaseController<HistorialTicket, Long, HistorialTicketDTO, HistorialTicketDAO, HistorialTicketMapper, HistorialTicketFilter> {

    public HistorialTicketController(BaseService<HistorialTicket, Long, HistorialTicketDAO> service, HistorialTicketMapper mapper, HistorialTicketFilter filter) {
        super(service, mapper, filter);
    }

    @Operation(summary = "Create a new HistorialTicket",
            description = "Creates a new HistorialTicket entity with the given DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "HistorialTicket created successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = HistorialTicketDTO.class))),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PostMapping
    public ResponseEntity<HistorialTicketDTO> create(
            @Parameter(description = "DTO representing the HistorialTicket to create", required = true)
            @RequestBody HistorialTicketDTO dto) {
        if (!"admin".equalsIgnoreCase(CurrentUser.getRole()) || !"agente".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return super.create(dto);
    }

    @Operation(summary = "Update an existing HistorialTicket",
            description = "Updates the HistorialTicket identified by the given ID with the data from the DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "HistorialTicket updated successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = HistorialTicketDTO.class))),
                @ApiResponse(responseCode = "404", description = "HistorialTicket not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PatchMapping("/{id}")
    public ResponseEntity<HistorialTicketDTO> update(
            @Parameter(description = "ID of the HistorialTicket to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "DTO containing updated HistorialTicket information", required = true)
            @RequestBody HistorialTicketDTO dto) {
        if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return super.update(id, dto);
    }

    @Operation(summary = "Get a HistorialTicket by ID",
            description = "Retrieves the HistorialTicket identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "200", description = "HistorialTicket retrieved successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = HistorialTicketDTO.class))),
                @ApiResponse(responseCode = "404", description = "HistorialTicket not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<HistorialTicketDTO> getById(
            @Parameter(description = "ID of the HistorialTicket to retrieve", required = true)
            @PathVariable Long id) {
        return super.getById(id);
    }

    @Operation(summary = "Get all HistorialTickets",
            description = "Retrieves all HistorialTicket entities.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of HistorialTickets",
                        content = @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = HistorialTicketDTO.class))))
            })
    @GetMapping
    public List<HistorialTicketDTO> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Delete a HistorialTicket",
            description = "Deletes the HistorialTicket identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "204", description = "HistorialTicket deleted successfully"),
                @ApiResponse(responseCode = "404", description = "HistorialTicket not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the HistorialTicket to delete", required = true)
            @PathVariable Long id) {
        if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return super.delete(id);
    }
}

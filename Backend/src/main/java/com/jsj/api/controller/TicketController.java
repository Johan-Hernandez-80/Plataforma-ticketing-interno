/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.util.BaseController;
import com.jsj.api.util.BaseService;
import com.jsj.api.util.mapper.TicketMapper;
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
@RequestMapping("/tickets")
@Tag(name = "Tickets", description = "Operations related to Ticket entities")
public class TicketController extends BaseController<Ticket, Long, TicketDTO, TicketDAO, TicketMapper> {

    public TicketController(BaseService<Ticket, Long, TicketDAO> service, TicketMapper mapper) {
        super(service, mapper);
    }

    @Operation(summary = "Create a new Ticket",
            description = "Creates a new Ticket entity with the given DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ticket created successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = TicketDTO.class))),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PostMapping
    public ResponseEntity<TicketDTO> create(
            @Parameter(description = "DTO representing the Ticket to create", required = true)
            @RequestBody TicketDTO dto) {
        return super.create(dto);
    }

    @Operation(summary = "Update an existing Ticket",
            description = "Updates the Ticket identified by the given ID with the data from the DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ticket updated successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = TicketDTO.class))),
                @ApiResponse(responseCode = "404", description = "Ticket not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @PatchMapping("/{id}")
    public ResponseEntity<TicketDTO> update(
            @Parameter(description = "ID of the Ticket to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "DTO containing updated Ticket information", required = true)
            @RequestBody TicketDTO dto) {
        return super.update(id, dto);
    }

    @Operation(summary = "Get a Ticket by ID",
            description = "Retrieves the Ticket identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ticket retrieved successfully",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = TicketDTO.class))),
                @ApiResponse(responseCode = "404", description = "Ticket not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getById(
            @Parameter(description = "ID of the Ticket to retrieve", required = true)
            @PathVariable Long id) {
        return super.getById(id);
    }

    @Operation(summary = "Get all Tickets",
            description = "Retrieves all Ticket entities.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of Tickets",
                        content = @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = TicketDTO.class))))
            })
    @GetMapping
    public List<TicketDTO> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Delete a Ticket",
            description = "Deletes the Ticket identified by the given ID.",
            responses = {
                @ApiResponse(responseCode = "204", description = "Ticket deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Ticket not found"),
                @ApiResponse(responseCode = "403", description = "Forbidden")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the Ticket to delete", required = true)
            @PathVariable Long id) {
        return super.delete(id);
    }
}

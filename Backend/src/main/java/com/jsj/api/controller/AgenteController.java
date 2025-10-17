package com.jsj.api.controller;

import com.jsj.api.service.TicketService;
import com.jsj.api.exception.AgenteInexistenteException;
import com.jsj.api.entity.dto.TicketDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agentes")
public class AgenteController {

  private TicketService ticketService;

  public AgenteController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @Operation(summary = "Consultar tickets filtrados para un agente", description = "Permite obtener todos los tickets aplicando filtros por estado, prioridad, agente o fecha.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Tickets obtenidos correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TicketDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Filtros inválidos o valores no reconocidos", content = @Content(schema = @Schema(example = "{ \"error\": \"Filtros inválidos o valores no reconocidos\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @GetMapping("/{agenteId}/tickets")
  public ResponseEntity<?> getTicketsFiltrados(
      @Parameter(description = "ID del agente asignado al ticket") @PathVariable Long agenteId,

      @Parameter(description = "Estado del ticket (ej: pendiente, cerrado, en progreso)") @RequestParam(required = false) String estado,

      @Parameter(description = "Prioridad del ticket (ej: urgente, importante, programado)") @RequestParam(required = false) String prioridad,

      @Parameter(description = "Fecha de creación del ticket (formato: yyyy-MM-dd)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
    try {
      List<TicketDTO> tickets = ticketService.findTicketsFiltrados(estado, prioridad, agenteId, fecha);
      return ResponseEntity.ok(tickets);
    } catch (AgenteInexistenteException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", e.getMessage()));
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of("error", "Filtros inválidos o valores no reconocidos"));
    }
  }
}

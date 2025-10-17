/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.exception.TicketInexistenteException;
import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.PrioridadDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.entity.mapper.TicketMapper;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.service.BaseService;
import com.jsj.api.entity.filter.TicketFilter;
import com.jsj.api.entity.filter.UsuarioFilter;
import com.jsj.api.exception.AgenteInexistenteException;
import com.jsj.api.exception.AsignacionInexistenteException;
import com.jsj.api.exception.CampoInvalidoException;
import com.jsj.api.exception.CategoriaInexistenteException;
import com.jsj.api.exception.DescripcionInvalidaException;
import com.jsj.api.exception.EstadoInvalidoException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.exception.NotAuthorizedException;
import com.jsj.api.exception.PrioridadInvalidaException;
import com.jsj.api.exception.TituloInvalidoException;
import com.jsj.api.exception.UsuarioInexistenteException;
import com.jsj.api.service.TicketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Juan José Molano Franco
 */
@RestController
@RequestMapping("/tickets")
@Tag(name = "Tickets", description = "Operaciones relacionadas con los tiquetes")
public class TicketController extends BaseController<Ticket, Long, TicketDTO> {

  private static final Logger log = LoggerFactory.getLogger(UsuarioFilter.class);

  private final TicketService service;

  public TicketController(TicketService service) {
    super(service);
    this.service = service;
  }

  @Operation(summary = "Crear un nuevo ticket", description = "Permite a un empleado autenticado crear un nuevo ticket en el sistema, "
      + "proporcionando los datos requeridos como categoría, título, descripción y prioridad.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Ticket creado correctamente", content = @Content(schema = @Schema(implementation = TicketDTO.class))),
      @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(example = "{ \"error\": \"Datos inválidos en la solicitud.\", \"detalles\": [\"Campo obligatorio faltante o incorrecto\"] }"))),
      @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content(schema = @Schema(example = "{ \"error\": \"Usuario no autorizado.\" }"))),
      @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content(schema = @Schema(example = "{ \"error\": \"Recurso no encontrado.\", \"detalles\": \"Usuario o categoría inexistente\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @PostMapping
  public ResponseEntity<?> createTicket(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo ticket", required = true, content = @Content(schema = @Schema(implementation = TicketDTO.class))) @Valid @RequestBody TicketDTO ticketDTO) {

    TicketDTO created;
    try {
      created = service.save(ticketDTO);
    } catch (UsuarioInexistenteException | CategoriaInexistenteException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of(
              "error", "Recurso no encontrado.",
              "detalle", ex.getMessage()));
    } catch (DescripcionInvalidaException | TituloInvalidoException | EstadoInvalidoException
        | PrioridadInvalidaException ex) {
      log.info("Error: ", ex);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of("error", "Parámetro invalido",
              "detalle", ex.getMessage()));
    } catch (InsufficientSavingPermissionsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of(
              "error", "Recurso no encontrado.",
              "detalle", ex.getMessage()));
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @Operation(summary = "Consultar tickets", description = "Permite a un empleado autenticado obtener la lista de tickets creados, "
      + "filtrando opcionalmente por estado, prioridad y usuario.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Tickets obtenidos correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TicketDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos", content = @Content(schema = @Schema(example = "{ \"error\": \"Parámetros de consulta inválidos.\", \"detalles\": [\"El valor 'urgenteee' no es una prioridad válida\"] }"))),
      @ApiResponse(responseCode = "401", description = "Autorización inválida", content = @Content(schema = @Schema(example = "{ \"error\": \"Autorización inválida.\", \"detalle\": [\"No tiene permiso para ver los tiquetes del usuario con id 78\"] }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @GetMapping
  public ResponseEntity<?> getTickets(
      @Parameter(description = "Estado del ticket (ej: En progreso, Pendiente, Cerrado)") @RequestParam(required = false) String estado,
      @Parameter(description = "Prioridad del ticket (ej: Urgente, Importante, Programado)") @RequestParam(required = false) String prioridad,
      @Parameter(description = "ID del usuario creador del ticket") @RequestParam(name = "usuario_id", required = false) Long usuarioId) {

    List<TicketDTO> tickets;
    try {
      tickets = service.findTickets(estado, prioridad, usuarioId);
      if (tickets == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of(
                "error", "Autorización inválida",
                "detalle", "No tiene permiso para ver los tiquetes del usuario con id " + usuarioId));
      }
      return ResponseEntity.ok(tickets);
    } catch (UsuarioInexistenteException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of(
              "error", "Parámetros de consulta inválidos.",
              "detalle", "El usuario " + usuarioId + " no es valido."));
    } catch (PrioridadInvalidaException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of(
              "error", "Parámetros de consulta inválidos.",
              "detalle", "La prioridad " + prioridad + " no es valida."));
    } catch (EstadoInvalidoException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of(
              "error", "Parámetros de consulta inválidos.",
              "detalle", "El estado " + estado + " no es valido."));
    }
  }

  @Operation(summary = "Obtener información de un ticket por ID", description = "Devuelve los detalles completos de un ticket existente, "
      + "siempre que el usuario tenga permisos para consultarlo.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ticket obtenido correctamente", content = @Content(schema = @Schema(implementation = TicketDTO.class))),
      @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(schema = @Schema(example = "{ \"error\": \"ID de ticket inválido.\", \"detalles\": \"El parámetro idTicket debe ser un número válido.\" }"))),
      @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content(schema = @Schema(example = "{ \"error\": \"No tiene permiso para ver este tiquete\" }"))),
      @ApiResponse(responseCode = "404", description = "Ticket no encontrado", content = @Content(schema = @Schema(example = "{ \"error\": \"No se encontró el ticket solicitado.\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @GetMapping("/{idTicket}")
  public ResponseEntity<?> getTicketById(
      @Parameter(description = "ID del ticket a consultar", required = true) @PathVariable Long idTicket) {
    TicketDTO ticket;
    try {
      ticket = service.findById(idTicket);
    } catch (NotAuthorizedException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("error", "No tiene permiso para ver este tiquete."));
    }

    if (ticket == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "No se encontró el ticket solicitado."));
    }

    return ResponseEntity.ok(ticket);
  }

  @Operation(summary = "Consultar comentarios de un ticket", description = "Devuelve la lista de comentarios asociados a un ticket específico, "
      + "mostrando el historial del proceso desde su creación hasta la resolución.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Comentarios obtenidos correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComentarioDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(schema = @Schema(example = "{ \"error\": \"ID de ticket inválido.\", \"detalles\": \"El parámetro idTicket debe ser un número válido.\" }"))),
      @ApiResponse(responseCode = "404", description = "Ticket no encontrado", content = @Content(schema = @Schema(example = "{ \"error\": \"No se encontro un ticket con este id\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @GetMapping("/{idTicket}/comentarios")
  public ResponseEntity<?> getComentariosByTicketId(
      @Parameter(description = "ID del ticket a consultar", required = true) @PathVariable Long idTicket) {
    List<ComentarioDTO> comentarios;
    try {
      comentarios = service.findComentariosByTicketId(idTicket);
    } catch (TicketInexistenteException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "No se encontro un ticket con este id"));
    }
    return ResponseEntity.ok(comentarios);
  }

  @Operation(summary = "Agregar un comentario a un ticket", description = "Permite a un agente agregar un comentario a un ticket existente, "
      + "siempre que tenga permiso para hacerlo.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Comentario creado correctamente", content = @Content(schema = @Schema(implementation = ComentarioDTO.class))),
      @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(example = "{ \"error\": \"Los datos enviados son inválidos o faltan campos obligatorios\" }"))),
      @ApiResponse(responseCode = "401", description = "Permisos insuficientes", content = @Content(schema = @Schema(example = "{ \"error\": \"No tiene suficientes permisos\" }"))),
      @ApiResponse(responseCode = "404", description = "Ticket no encontrado", content = @Content(schema = @Schema(example = "{ \"error\": \"El ticket no existe o no fue encontrado\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @PostMapping("/{ticketId}/comments"
      + "")
  public ResponseEntity<?> createComentario(
      @Parameter(description = "ID del ticket al cual se agregará el comentario", required = true) @PathVariable Long ticketId,
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del comentario", required = true, content = @Content(schema = @Schema(implementation = ComentarioDTO.class))) @Valid @RequestBody ComentarioDTO comentarioDTO) {
    ComentarioDTO created;
    try {
      created = service.addComentario(ticketId, comentarioDTO);
    } catch (TicketInexistenteException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "El ticket no existe o no fue encontrado"));
    } catch (CampoInvalidoException | UsuarioInexistenteException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of("error", ex.getMessage()));
    } catch (InsufficientSavingPermissionsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("error", ex.getMessage()));
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @Operation(summary = "Actualizar prioridad de un ticket", description = "Permite actualizar la prioridad de un ticket existente, "
      + "siempre que el usuario tenga permiso para modificarlo.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Prioridad actualizada correctamente", content = @Content(schema = @Schema(implementation = TicketDTO.class))),
      @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(example = "{ \"error\": \"El valor de la prioridad no es válido\" }"))),
      @ApiResponse(responseCode = "404", description = "Ticket no encontrado", content = @Content(schema = @Schema(example = "{ \"error\": \"El ticket no existe\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @PatchMapping("/{idTicket}/prioridad")
  public ResponseEntity<?> updatePrioridad(
      @PathVariable Long idTicket,
      @RequestBody PrioridadDTO prioridad) {
    try {
      TicketDTO ticket = service.updatePrioridad(idTicket, prioridad);
      return ResponseEntity.ok(ticket);
    } catch (TicketInexistenteException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "El ticket no existe"));
    } catch (PrioridadInvalidaException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of("error", "El valor de la prioridad no es válido"));
    }
  }

  @Operation(summary = "Cerrar un ticket", description = "Permite cerrar un ticket existente cambiando su estado a 'cerrado'.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ticket cerrado correctamente", content = @Content(schema = @Schema(implementation = TicketDTO.class))),
      @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(schema = @Schema(example = "{ \"error\": \"Solicitud inválida para cerrar el ticket\" }"))),
      @ApiResponse(responseCode = "404", description = "Ticket no encontrado", content = @Content(schema = @Schema(example = "{ \"error\": \"El ticket no fue encontrado\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @PatchMapping("/{idTicket}/cerrar")
  public ResponseEntity<?> cerrarTicket(
      @Parameter(description = "ID del ticket a cerrar", required = true) @PathVariable Long idTicket) {
    try {
      TicketDTO cerrado = service.cerrarTicket(idTicket);
      return ResponseEntity.ok(cerrado);
    } catch (TicketInexistenteException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "El ticket no fue encontrado"));
    }
  }

  @Operation(summary = "Reasignar un ticket a un agente", description = "Permite al administrador reasignar un ticket existente a un agente disponible.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Ticket reasignado correctamente", content = @Content(schema = @Schema(implementation = TicketDTO.class))),
      @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(example = "{ \"error\": \"Los datos enviados son inválidos\" }"))),
      @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content(schema = @Schema(example = "{ \"error\": \"No tiene permiso para usar este endpoint\" }"))),
      @ApiResponse(responseCode = "404", description = "Ticket o agente no encontrado", content = @Content(schema = @Schema(example = "{ \"error\": \"No se encontró el ticket o el agente solicitado\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @PostMapping("/{idTicket}")
  public ResponseEntity<?> reasignarTicket(
      @Parameter(description = "ID del ticket a reasignar", required = true) @PathVariable Long idTicket,
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos con el ID del nuevo agente", required = true, content = @Content(schema = @Schema(example = "{ \"agenteId\": 123 }"))) @RequestBody Map<String, Long> body)
      throws AsignacionInexistenteException {
    if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    Long agenteId = body.get("agenteId");
    try {
      TicketDTO reasignado = service.reasignarTicket(idTicket, agenteId);
      return ResponseEntity.status(HttpStatus.CREATED).body(reasignado);
    } catch (TicketInexistenteException | AgenteInexistenteException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "No se encontró el ticket o el agente solicitado"));
    }
  }

  @Operation(summary = "Consultar tickets filtrados", description = "Permite obtener todos los tickets aplicando filtros por estado, prioridad, agente o fecha.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Tickets obtenidos correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TicketDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Filtros inválidos o valores no reconocidos", content = @Content(schema = @Schema(example = "{ \"error\": \"Filtros inválidos o valores no reconocidos\" }"))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
  })
  @GetMapping("/filtered")
  public ResponseEntity<?> getTicketsFiltrados(
      @Parameter(description = "Estado del ticket (ej: pendiente, cerrado, en progreso)") @RequestParam(required = false) String estado,
      @Parameter(description = "Prioridad del ticket (ej: urgente, importante, programado)") @RequestParam(required = false) String prioridad,
      @Parameter(description = "ID del agente asignado al ticket") @RequestParam(required = false) Long agenteId,
      @Parameter(description = "Fecha de creación del ticket (formato: yyyy-MM-dd)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
    try {
      List<TicketDTO> tickets = service.findTicketsFiltrados(estado, prioridad, agenteId, fecha);
      return ResponseEntity.ok(tickets);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of("error", "Filtros inválidos o valores no reconocidos"));
    }
  }

}

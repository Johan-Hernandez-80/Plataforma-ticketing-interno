/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.entity.mapper.TicketMapper;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.service.BaseService;
import com.jsj.api.entity.filter.TicketFilter;
import com.jsj.api.exception.CategoriaInexistenteException;
import com.jsj.api.exception.EstadoInvalidoException;
import com.jsj.api.exception.NotAuthorizedException;
import com.jsj.api.exception.PrioridadInvalidaException;
import com.jsj.api.exception.UsuarioInexistenteException;
import com.jsj.api.service.TicketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@Tag(name = "Tickets", description = "Operaciones relacionadas con los tiquetes")
public class TicketController extends BaseController<Ticket, Long, TicketDTO> {

    private final TicketService service;

    public TicketController(TicketService service) {
        super(service);
        this.service = service;
    }

    @Operation(
            summary = "Crear un nuevo ticket",
            description = "Permite a un empleado autenticado crear un nuevo ticket en el sistema, "
            + "proporcionando los datos requeridos como categoría, título, descripción y prioridad."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ticket creado correctamente",
                content = @Content(schema = @Schema(implementation = TicketDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Datos inválidos en la solicitud.\", \"detalles\": [\"Campo obligatorio faltante o incorrecto\"] }"))),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Recurso no encontrado.\", \"detalles\": \"Usuario o categoría inexistente\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @PostMapping
    public ResponseEntity<?> createTicket(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo ticket",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TicketDTO.class))
            )
            @RequestBody TicketDTO ticketDTO) {

        TicketDTO created;
        try {
            created = service.save(ticketDTO);
        } catch (CategoriaInexistenteException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Recurso no encontrado.",
                            "detalle", "La categoría " + ticketDTO.getCategoriaId() + " no existe en la base de datos."
                    ));
        } catch (UsuarioInexistenteException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Recurso no encontrado.",
                            "detalle", "El usuario " + ticketDTO.getUsuarioId() + " no existe en la base de datos."
                    ));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(
            summary = "Consultar tickets",
            description = "Permite a un empleado autenticado obtener la lista de tickets creados, "
            + "filtrando opcionalmente por estado, prioridad y usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tickets obtenidos correctamente",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TicketDTO.class)))),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Parámetros de consulta inválidos.\", \"detalles\": [\"El valor 'urgenteee' no es una prioridad válida\"] }"))),
        @ApiResponse(responseCode = "401", description = "Autorización inválida",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Autorización inválida.\", \"detalle\": [\"No tiene permiso para ver los tiquetes del usuario con id 78\"] }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @GetMapping
    public ResponseEntity<?> getTickets(
            @Parameter(description = "Estado del ticket (ej: En progreso, Programado, Cerrado)")
            @RequestParam(required = false) String estado,
            @Parameter(description = "Prioridad del ticket (ej: Urgente, Importante, Programado)")
            @RequestParam(required = false) String prioridad,
            @Parameter(description = "ID del usuario creador del ticket")
            @RequestParam(name = "usuario_id", required = false) Long usuarioId
    ) {

        List<TicketDTO> tickets;
        try {
            tickets = service.findTickets(estado, prioridad, usuarioId);
            if (tickets == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of(
                                "error", "Autorización inválida",
                                "detalle", "No tiene permiso para ver los tiquetes del usuario con id " + usuarioId
                        ));
            }
            return ResponseEntity.ok(tickets);
        } catch (UsuarioInexistenteException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Parámetros de consulta inválidos.",
                            "detalle", "El usuario " + usuarioId + " no es valido."
                    ));
        } catch (PrioridadInvalidaException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Parámetros de consulta inválidos.",
                            "detalle", "La prioridad " + prioridad + " no es valida."
                    ));
        } catch (EstadoInvalidoException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Parámetros de consulta inválidos.",
                            "detalle", "El estado " + estado + " no es valido."
                    ));
        }
    }

    @Operation(
            summary = "Obtener información de un ticket por ID",
            description = "Devuelve los detalles completos de un ticket existente, "
            + "siempre que el usuario tenga permisos para consultarlo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket obtenido correctamente",
                content = @Content(schema = @Schema(implementation = TicketDTO.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                content = @Content(schema = @Schema(example = "{ \"error\": \"ID de ticket inválido.\", \"detalles\": \"El parámetro idTicket debe ser un número válido.\" }"))),
        @ApiResponse(responseCode = "401", description = "No autorizado",
                content = @Content(schema = @Schema(example = "{ \"error\": \"No tiene permiso para ver este tiquete\" }"))),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
                content = @Content(schema = @Schema(example = "{ \"error\": \"No se encontró el ticket solicitado.\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @GetMapping("/{idTicket}")
    public ResponseEntity<?> getTicketById(
            @Parameter(description = "ID del ticket a consultar", required = true)
            @PathVariable Long idTicket
    ) {
        TicketDTO ticket;
        try {
            ticket = service.findById(idTicket);
        } catch (NotAuthorizedException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "No tiene permiso para ver este tiquete."));
        }

        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "No se encontró el ticket solicitado."));
        }

        return ResponseEntity.ok(ticket);
    }

    @Operation(
            summary = "Consultar comentarios de un ticket",
            description = "Devuelve la lista de comentarios asociados a un ticket específico, "
            + "mostrando el historial del proceso desde su creación hasta la resolución."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comentarios obtenidos correctamente",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComentarioDTO.class)))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                content = @Content(schema = @Schema(example = "{ \"error\": \"ID de ticket inválido.\", \"detalles\": \"El parámetro idTicket debe ser un número válido.\" }"))),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
                content = @Content(schema = @Schema(example = "{ \"error\": \"No se encontro un ticket con este id\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @GetMapping("/{idTicket}/comentarios")
    public ResponseEntity<?> getComentariosByTicketId(
            @Parameter(description = "ID del ticket a consultar", required = true)
            @PathVariable Long idTicket
    ) {
        List<ComentarioDTO> comentarios = service.findComentariosByTicketId(idTicket);

        if (comentarios == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "No se encontro un ticket con este id"));
        }

        return ResponseEntity.ok(comentarios);
    }

    @Operation(
            summary = "Agregar un comentario a un ticket",
            description = "Permite a un agente agregar un comentario a un ticket existente, "
            + "siempre que tenga permiso para hacerlo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Comentario creado correctamente",
                content = @Content(schema = @Schema(implementation = ComentarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Los datos enviados son inválidos o faltan campos obligatorios\" }"))),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
                content = @Content(schema = @Schema(example = "{ \"error\": \"El ticket no existe o no fue encontrado\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @PostMapping("/{ticketId}/comments"
            + "")
    public ResponseEntity<?> createComentario(
            @Parameter(description = "ID del ticket al cual se agregará el comentario", required = true)
            @PathVariable Long ticketId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del comentario",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ComentarioDTO.class))
            )
            @RequestBody ComentarioDTO comentarioDTO
    ) {
        ComentarioDTO created = service.addComentario(ticketId, comentarioDTO);
        if (created == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "El ticket no existe o no fue encontrado"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
/*
    @Operation(
            summary = "Actualizar prioridad de un ticket",
            description = "Permite actualizar la prioridad de un ticket existente, "
            + "siempre que el usuario tenga permiso para modificarlo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Prioridad actualizada correctamente",
                content = @Content(schema = @Schema(implementation = TicketDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
                content = @Content(schema = @Schema(example = "{ \"error\": \"El valor de la prioridad no es válido\" }"))),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
                content = @Content(schema = @Schema(example = "{ \"error\": \"El ticket no existe\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @PatchMapping("/{idTicket}/prioridad")
    public ResponseEntity<?> updatePrioridad(
            @PathVariable Long idTicket,
            @RequestBody PrioridadDTO prioridadDTO
    ) {
        try {
            String nuevaPrioridad = service.updatePrioridad(idTicket, prioridadDTO.getPrioridad());
            return ResponseEntity.ok(Map.of("prioridad", nuevaPrioridad));
        } catch (TicketInexistenteException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "El ticket no existe"));
        } catch (PrioridadInvalidaException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "El valor de la prioridad no es válido"));
        }
    }
*/
}

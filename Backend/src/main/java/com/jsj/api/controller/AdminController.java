/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.service.AdminService;
import com.jsj.api.service.BaseService;
import com.jsj.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Juan José Molano Franco
 */
@RestController
@RequestMapping("/admin")
@Tag(name = "Admin", description = "Operaciones relacionadas con el admin")
public class AdminController  {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @Operation(
            summary = "Obtener métricas del sistema",
            description = "Permite al administrador visualizar las métricas generales del sistema, incluyendo tickets abiertos, cerrados, empleados y agentes activos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Métricas obtenidas correctamente",
                content = @Content(schema = @Schema(example = "{ \"ticketsAbiertos\": 15, \"ticketsCerrados\": 40, \"empleadosActivos\": 25, \"agentesActivos\": 5 }"))),
        @ApiResponse(responseCode = "401", description = "Token inválido o usuario no autorizado",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Autorización inválida.\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @GetMapping("/estadisticas")
    public ResponseEntity<?> getEstadisticas() {
        if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, Object> estadisticas = Map.of(
                "ticketsAbiertos", service.countTicketsAbiertos(),
                "ticketsCerrados", service.countTicketsCerrados(),
                "empleadosActivos", service.countEmpleadosActivos(),
                "agentesActivos", service.countAgentesActivos()
        );
        return ResponseEntity.ok(estadisticas);
    }

}

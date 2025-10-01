/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.service.BaseService;
import com.jsj.api.entity.*;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.*;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.service.UsuarioService;
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
public class UsuarioController extends BaseController<Usuario, Long, UsuarioDTO> {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        super(service);
        this.service = service;
    }

    @Operation(
            summary = "Consultar notificaciones de un usuario",
            description = "Permite a un usuario autenticado obtener la lista de notificaciones "
            + "generadas por el sistema, relacionadas con tickets, asignaciones o actualizaciones. "
            + "Si el usuario no existe o los parámetros son inválidos, se devuelven los errores correspondientes."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notificaciones obtenidas correctamente",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Notificacion.class)))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Solicitud inválida.\", \"detalles\": \"Los parámetros enviados son incorrectos.\" }"))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                content = @Content(schema = @Schema(example = "{ \"mensaje\": \"Usuario no encontrado.\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @GetMapping("/{idUsuario}/notificaciones")
    public ResponseEntity<?> getNotificacionesById(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long idUsuario) {
        List<NotificacionDTO> notificaciones = service.getNotificacionesById(idUsuario);
        if (notificaciones == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Usuario no encontrado."));
        }
        return ResponseEntity.ok(notificaciones);
    }

    @Operation(
            summary = "Modificar datos personales de un usuario",
            description = "Permite a un usuario autenticado modificar parcialmente su perfil, incluyendo correo personal y contraseña. "
            + "Si los datos enviados son inválidos, el usuario no existe, o ocurre un error interno, se devuelven los errores correspondientes."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Datos inválidos.\", \"detalles\": [\"El correo electrónico no es válido\", \"La contraseña debe tener mínimo 8 caracteres\"] }"))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                content = @Content(schema = @Schema(example = "{ \"mensaje\": \"Usuario no encontrado.\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @PatchMapping("/{idUsuario}")
    public ResponseEntity<?> updateUsuario(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long idUsuario,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos a actualizar (parcialmente)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))
            )
            @RequestBody UsuarioDTO usuarioDTO) {

        UsuarioDTO updated = service.updateUsuario(idUsuario, usuarioDTO);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Usuario no encontrado."));
        }

        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Crear cuenta de usuario",
            description = "Permite al administrador crear cuentas de usuarios con rol de empleado o agente. "
            + "Si los datos enviados son inválidos o ocurre un error interno, se devuelven los errores correspondientes."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Los datos enviados son inválidos o faltan campos obligatorios\" }"))),
        @ApiResponse(responseCode = "401", description = "No autorizado",
                content = @Content(schema = @Schema(example = "{ \"error\": \"No tiene permiso para usar este endpoint\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor. Intente nuevamente más tarde.\" }")))
    })
    @PostMapping
    public ResponseEntity<?> createUsuario(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo usuario",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))
            )
            @RequestBody UsuarioDTO usuarioDTO) {

        if (!"admin".equalsIgnoreCase(CurrentUser.getRole())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UsuarioDTO created = service.save(usuarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

}

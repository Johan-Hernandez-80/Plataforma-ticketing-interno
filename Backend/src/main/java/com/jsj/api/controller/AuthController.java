/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.controller;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dto.LoginRequest;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.entity.mapper.UsuarioMapper;
import com.jsj.api.security.CurrentUser;
import com.jsj.api.security.JwtUtils;
import com.jsj.api.service.RolService;
import com.jsj.api.service.UsuarioService;
import com.jsj.api.entity.filter.UsuarioFilter;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Juan José Molano Franco
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and JWT token retrieval")
public class AuthController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;
    private final UsuarioFilter filter;
    private final RolService rolService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, UsuarioMapper mapper, UsuarioFilter filter, RolService rolService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.mapper = mapper;
        this.filter = filter;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(
            summary = "Login del usuario",
            description = "Autentica un usuario usando email y contraseña y retorna un token valido",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credenciales para login",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequest.class)
                    )
            ),
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Autenticación exitosa, token retornado",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(
                                        example = "{\"token\": \"<jwt-token>\"}"
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "401",
                        description = "Autenticación fallida, credenciales inválidas",
                        content = @Content
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error interno del servidor",
                        content = @Content
                )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Parameter(description = "Credenciales se login, email y contraseña", required = true)
            @RequestBody LoginRequest request
    ) {
        Usuario user = usuarioService.validateCredentials(request.getEmail(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas. Verifique su email y contraseña."));
        }

        String token = JwtUtils.generateToken(user);
        Map<String, String> body = Map.of("token", token);
        return ResponseEntity.ok(body);
    }

}

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
            summary = "Login user",
            description = "Authenticates a user using email and password and returns a JWT token",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credentials for login",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequest.class)
                    )
            ),
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Authentication successful, token returned",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(
                                        example = "{\"token\": \"<jwt-token>\"}"
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "401",
                        description = "Authentication failed, invalid credentials",
                        content = @Content
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal server error",
                        content = @Content
                )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Parameter(description = "Login credentials including email and password", required = true)
            @RequestBody LoginRequest request
    ) {
        Usuario user = usuarioService.validateCredentials(request.getEmail(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = JwtUtils.generateToken(user);
        Map<String, String> body = Map.of("token", token);
        return ResponseEntity.ok(body);
    }

    @Operation(
            summary = "Register new user",
            description = "Creates a new user account with encrypted password and assigned role",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User data for registration",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UsuarioDTO.class)
                    )
            ),
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User registered successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UsuarioDTO.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Validation errors",
                        content = @Content
                ),
                @ApiResponse(
                        responseCode = "409",
                        description = "Database constraint violation or duplicate key",
                        content = @Content
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal server error",
                        content = @Content
                )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(
            @Parameter(description = "User registration data", required = true)
            @RequestBody UsuarioDTO dto
    ) {
        Set<String> perms = CurrentUser.getPermissions();

        Usuario usuario = mapper.toEntity(dto);

        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));

        if (rolService.isIdRolAnAgente(dto.getRolId())) {
            usuario.setRol(rolService.getRolAgente());
        } else {
            usuario.setRol(rolService.getRolUsuario());
        }
        filter.filterEntity(usuario, perms);
        Usuario saved = usuarioService.save(usuario);
        return ResponseEntity.ok(filter.filterDTO(mapper.toDTO(saved), perms));
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.security;

import com.jsj.api.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Juan José Molano Franco
 */
public class JwtAuthFilter extends OncePerRequestFilter {

    private final PermissionService permissionService;
    private final UsuarioService usuarioService;

    public JwtAuthFilter(PermissionService permissionService, UsuarioService usuarioService) {
        this.permissionService = permissionService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                // Validate token and extract userId
                String userId = JwtUtils.extractUserId(token);

                // Fetch permissions from DB or cache
                Set<String> permissions = permissionService.getPermissionsForUser(Long.parseLong(userId));

                // Fetch user role
                String role = usuarioService.findById(Long.parseLong(userId))
                        .map(usuario -> usuario.getRol().getNombre())
                        .orElseThrow(() -> new RuntimeException("User not found"));

                // Build authentication
                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(userId, null, List.of());
                // Attach permissions to details
                authToken.setDetails(Map.of(
                        "role", role,
                        "permissions", permissions
                ));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}

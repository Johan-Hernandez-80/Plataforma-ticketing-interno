/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Juan José Molano Franco
 */
public class JwtAuthFilter extends OncePerRequestFilter {
    
    private final PermissionService permissionService;

    public JwtAuthFilter(PermissionService permissionService) {
        this.permissionService = permissionService;
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

                // Build authentication
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userId, null, List.of());
                // Attach permissions to details
                authToken.setDetails(permissions);

                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}


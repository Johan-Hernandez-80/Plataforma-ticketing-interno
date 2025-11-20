/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.security;

import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.service.RolService;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class JwtUtils {

    private static final long EXPIRATION_MS = 3600000; // 1 hora
    private final Key key;
    private final RolService rolService;

    public JwtUtils(@Value("${JWT_SECRET}") String secret, RolService rolService) {
        this.rolService = rolService;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UsuarioDTO user) {
        return Jwts.builder()
            .setSubject(user.getId().toString())
            .claim("role", rolService.findRolNombreById(user.getRolId()).get())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUserId(String token) throws JwtException {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.security;

import com.jsj.api.entity.Usuario;
import com.jsj.api.service.RolService;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class JwtUtils {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String SECRET = dotenv.get("JWT_SECRET");
    private static final long EXPIRATION_MS = 3600000; // 1 hour
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final RolService rolService;

    public JwtUtils(RolService rolService) {
        this.rolService = rolService;
    }
    
    public String generateToken(Usuario user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("role", rolService.findRolNombreById(user.getRolId()).get())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserId(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

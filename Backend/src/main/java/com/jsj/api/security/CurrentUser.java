/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.security;

import com.jsj.api.entity.filter.UsuarioFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Juan José Molano Franco
 */
public class CurrentUser {
    
    private static final Logger log = LoggerFactory.getLogger(UsuarioFilter.class);

    public static Set<String> getPermissions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities() != null) {
            return auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    public static String getRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getDetails() instanceof Map<?, ?> details) {
            return (String) details.get("role");
        }
        return null;
    }

    public static String getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return (String) auth.getPrincipal();
        }
        return null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.*;

/**
 *
 * @author Juan José Molano Franco
 */
public class CurrentUser {

    public static Set<String> getPermissions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getDetails() instanceof Map<?, ?> rawDetails) {
            Map<String, Object> details = (Map<String, Object>) rawDetails;
            Object permsObj = details.get("permissions");
            if (permsObj instanceof Set<?>) {
                return (Set<String>) permsObj;
            }
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

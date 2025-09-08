/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;
import java.util.Set;


/**
 *
 * @author Juan José Molano Franco
 */
public class CurrentUser {

    @SuppressWarnings("unchecked")
    public static Set<String> getPermissions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getDetails() instanceof Set<?> perms) {
            return (Set<String>) perms;
        }
        return Collections.emptySet();
    }

    public static String getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return (String) auth.getPrincipal();
        }
        return null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.constants;

import java.util.Set;

/**
 *
 * @author Juan José Molano Franco
 */
public final class TicketConstants {

    private TicketConstants() {
    }

    public static final Set<String> ESTADOS_VALIDOS
            = Set.of("En progreso", "Pendiente", "Cerrado");

    public static final Set<String> PRIORIDADES_VALIDAS
            = Set.of("Urgente", "Importante", "Programado");

    private static Set<String> ESTADOS_VALIDOS_CAPS;
    private static Set<String> PRIORIDADES_VALIDAS_CAPS;

    static {
        ESTADOS_VALIDOS_CAPS = capitalize(ESTADOS_VALIDOS);
        PRIORIDADES_VALIDAS_CAPS = capitalize(PRIORIDADES_VALIDAS);
    }

    private static Set<String> capitalize(Set<String> set) {
        Set<String> capsSet = Set.of();
        for (String s : set) {
            capsSet.add(s.toUpperCase());
        }
        return capsSet;
    }

    public static boolean isPrioridadIgnoringCaps(String prioridad) {
        return PRIORIDADES_VALIDAS_CAPS.contains(prioridad.toUpperCase());
    }

    public static boolean isEstadoIgnoringCaps(String estado) {
        return ESTADOS_VALIDOS_CAPS.contains(estado.toUpperCase());
    }

    public static String getPrioridad(String prioridad) {
        if (prioridad == null) {
            return null;
        }
        String upper = prioridad.toUpperCase();
        for (String valid : PRIORIDADES_VALIDAS) {
            if (valid.toUpperCase().equals(upper)) {
                return valid;
            }
        }
        return null;
    }

    public static String getEstado(String estado) {
        if (estado == null) {
            return null;
        }
        String upper = estado.toUpperCase();
        for (String valid : ESTADOS_VALIDOS) {
            if (valid.toUpperCase().equals(upper)) {
                return valid;
            }
        }
        return null;
    }
}

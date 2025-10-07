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
            = Set.of("En progreso","Programado","Cerrado");

    public static final Set<String> PRIORIDADES_VALIDAS
            = Set.of("Urgente","Importante","Programado");
}

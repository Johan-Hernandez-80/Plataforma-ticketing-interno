/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jsj.api.exception;

/**
 *
 * @author Juan José Molano Franco
 */
public class AgenteInexistenteException extends Exception {
    
    public AgenteInexistenteException(Long id) {
        super(String.valueOf(id));
    }

    public AgenteInexistenteException(String message) {
        super(message);
    }

}

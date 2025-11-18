/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.exception;

/**
 *
 * @author Juan José Molano Franco
 */
public class CategoriaInexistenteException extends Exception {

    public CategoriaInexistenteException(Long categoriaId) {
        super(String.valueOf(categoriaId));
    }

    public CategoriaInexistenteException(String message) {
        super(message);
    }

}

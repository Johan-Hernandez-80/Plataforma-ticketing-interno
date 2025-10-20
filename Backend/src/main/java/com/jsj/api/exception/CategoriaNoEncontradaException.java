package com.jsj.api.exception;

/**
 * Excepción lanzada cuando no se encuentra una categoría por id.
 */
public class CategoriaNoEncontradaException extends RuntimeException {

    public CategoriaNoEncontradaException() {
        super();
    }

    public CategoriaNoEncontradaException(String message) {
        super(message);
    }

    public CategoriaNoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
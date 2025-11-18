package com.jsj.api.exception;

/**
 * Excepción que indica que ya existe una categoría con el mismo nombre.
 */
public class CategoriaExistenteException extends RuntimeException {

    public CategoriaExistenteException() {
        super();
    }

    public CategoriaExistenteException(String message) {
        super(message);
    }

    public CategoriaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.catalogo.catalogoservice.exception;


public class InventarioServiceException extends RuntimeException {

    public InventarioServiceException(String message) {
        super(message);
    }

    public InventarioServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

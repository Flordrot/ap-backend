package com.portfolioFBD.Exceptions;

public class UserNoEncontradoException extends Exception {

    public UserNoEncontradoException() {
    }

    public UserNoEncontradoException(Integer id) {
        super("No se encontró un usuario con id = " + id);
    }
}

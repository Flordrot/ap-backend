package com.portfolioFBD.Exceptions;

public class AdminNoEncontradoException extends Exception {

    public AdminNoEncontradoException() {
    }

    public AdminNoEncontradoException(Integer id) {
        super("No se encontrĂ³ un administrador con id = " + id);
    }
    
}

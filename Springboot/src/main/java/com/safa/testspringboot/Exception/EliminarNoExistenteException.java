package com.safa.testspringboot.Exception;

public class EliminarNoExistenteException extends RuntimeException {


    public EliminarNoExistenteException(String mensaje){
        super(mensaje);
    }

}

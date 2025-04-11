package com.alexisrock.microservicioRest.Common.exceptions;

public class ApiException  extends  RuntimeException {


    public ApiException(String mensaje){
        super(mensaje);
    }
}

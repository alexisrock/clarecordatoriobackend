package com.alexisrock.microservicioRest.login.application.Login;

public class LoginResponse {

    private String token;
    private Integer idUsuario;

    public  LoginResponse(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LoginResponse(String token, Integer idUsuario) {
        this.token = token;
        this.idUsuario = idUsuario;
    }
}

package com.alexisrock.microservicioRest.login.application.Login;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "El correo es obligatoria")
    private String Email;
    @NotNull(message = "El password es obligatorio")
    private String Password;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

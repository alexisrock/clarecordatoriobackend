package com.alexisrock.microservicioRest.login.application.Login;

public interface LoginService {
   LoginResponse login(LoginRequest request);

   String searchEmailUsers(String email);
}

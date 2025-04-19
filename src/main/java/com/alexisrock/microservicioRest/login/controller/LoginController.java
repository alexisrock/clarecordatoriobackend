package com.alexisrock.microservicioRest.login.controller;
import com.alexisrock.microservicioRest.login.application.Login.LoginRequest;
import com.alexisrock.microservicioRest.login.application.Login.LoginResponse;

import com.alexisrock.microservicioRest.login.application.Login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired(required = true)
    private  LoginService service;


    @PostMapping("/create")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        try {
            LoginResponse response = new LoginResponse();
            response = service.login(request);
            return ResponseEntity.ok(response);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


}

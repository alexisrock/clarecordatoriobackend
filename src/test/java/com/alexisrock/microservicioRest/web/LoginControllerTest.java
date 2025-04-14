package com.alexisrock.microservicioRest.web;

import com.alexisrock.microservicioRest.Common.exceptions.ApiException;
import com.alexisrock.microservicioRest.login.application.Login.LoginRequest;
import com.alexisrock.microservicioRest.login.application.Login.LoginResponse;
import com.alexisrock.microservicioRest.login.application.Login.LoginService;
import com.alexisrock.microservicioRest.login.controller.LoginController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LoginControllerTest {


    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    @Test
    void testLoginSuccess() throws Exception{
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("alexisrock20@gmail.com");
        request.setPassword("MTIzNDU2");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGV4aXNyb2NrMjBAZ21haWwuY29tIiwiaXNzIjoiaHR0cDovLzE5MC42Ni4xMi41MDo4OTA5IiwiYXVkIjoiaHR0cDovLzE5MC42Ni4xMi41MDo4OTA5IiwiaWF0IjoxNzQ0NjUwMTg3LCJuYmYiOjE3NDQ2NTAxODcsImV4cCI6MTc0NDY4NjE4NywidW5pcXVlX25hbWUiOiJhbGV4aXNyb2NrMjBAZ21haWwuY29tIn0.r8G1zess2xC-hfGNBMtjwpvvb_CVEw3MOupkfWj8XIU");

        Mockito.when(loginService.login(request)).thenReturn(expectedResponse);

        ResponseEntity<?> responseEntity = loginController.login(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        LoginResponse actualResponse = (LoginResponse) responseEntity.getBody();
        assertEquals(expectedResponse.getToken(), actualResponse.getToken());
    }

    @Test
    void testLoginServiceThrowsException() throws Exception {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("testUser");
        request.setPassword("testPassword");

        String errorMessage = "Error during login";
        Mockito.when(loginService.login(request)).thenThrow(new ApiException(errorMessage));

        // Act
        ResponseEntity<?> responseEntity = loginController.login(request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());

        // Verify that the service method was called
        Mockito.verify(loginService, Mockito.times(1)).login(request);
    }



}

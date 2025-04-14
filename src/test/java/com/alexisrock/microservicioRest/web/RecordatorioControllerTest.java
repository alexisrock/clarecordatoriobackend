package com.alexisrock.microservicioRest.web;

import com.alexisrock.microservicioRest.Common.BaseResponse;
import com.alexisrock.microservicioRest.Common.exceptions.ApiException;
import com.alexisrock.microservicioRest.login.application.Login.LoginResponse;
import com.alexisrock.microservicioRest.login.application.Login.LoginService;
import com.alexisrock.microservicioRest.login.controller.LoginController;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioRequest;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioResponse;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioService;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioUpdateRequest;
import com.alexisrock.microservicioRest.recordatorio.controller.RecordatorioController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RecordatorioControllerTest {


    @InjectMocks
    private RecordatorioController controller;

    @Mock
    private RecordatorioService service;

    @Test
    void testgetRecordatoriosByIdSucces() throws ParseException {
        Integer id = 1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha =  formatter.parse("2024-05-11");
        List<RecordatorioResponse> responseList = List.of(
                new RecordatorioResponse(1, "Hacer el almuerzo",fecha, Time.valueOf("12:00:00"), false, true, true, 1) {
                },
                new RecordatorioResponse(1, "Hacer el almuerzo", fecha, Time.valueOf("12:00:00"), false, true, true, 1) {
                }
        );


        Mockito.when(service.getRecordatoriosById(id)).thenReturn(responseList);
        ResponseEntity<?> responseEntity = controller.getRecordatoriosById(id);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        List<RecordatorioResponse> actualResponse = (List<RecordatorioResponse>) responseEntity.getBody();
    }


    @Test
    void testcreateRecordatorioSuccess() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha =  formatter.parse("2024-05-11");
        RecordatorioRequest request = new RecordatorioRequest();
        request.setDescripcion( "Hacer el almuerzo");
        request.setFechaRegistro(fecha);
        request.setHora(Time.valueOf("12:00:00"));
        request.setEsRecurente(true);
        request.setEstado(true);
        request.setIdUsuario(1);

        BaseResponse response = new BaseResponse();
        response.setMessage("Recordatorio actualizado con exito.");
        Mockito.when(service.create(request)).thenReturn(response);
        ResponseEntity<?> responseEntity = controller.createRecordatorio(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        BaseResponse actualResponse = (BaseResponse) responseEntity.getBody();
        assertEquals(actualResponse.getMessage(), response.getMessage());
    }

    @Test
    void testcreateRecordatorioThrowException() throws ParseException {


        RecordatorioRequest request = null;


        BaseResponse response = new BaseResponse();
        response.setMessage("Recordatorio actualizado con exito.");
        Mockito.when(service.create(request)).thenThrow(new ApiException("error Message"));
        ResponseEntity<?> responseEntity = controller.createRecordatorio(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());


    }



    @Test
    void testPatchRecordatorioSuccess() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha =  formatter.parse("2024-05-11");
        RecordatorioUpdateRequest request = new RecordatorioUpdateRequest();
        request.setId(1);
        request.setDescripcion( "Hacer el almuerzo");
        request.setFechaRegistro(fecha);
        request.setHora(Time.valueOf("12:00:00"));
        request.setEsRecurente(true);
        request.setEstado(true);
        request.setIdUsuario(1);

        BaseResponse response = new BaseResponse();
        response.setMessage("Recordatorio actualizado con exito.");
        Mockito.when(service.update(request)).thenReturn(response);
        ResponseEntity<?> responseEntity = controller.patchRecordatorio(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        BaseResponse actualResponse = (BaseResponse) responseEntity.getBody();
        assertEquals(actualResponse.getMessage(), response.getMessage());
    }

    @Test
    void testPatchRecordatorioThrowException() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha =  formatter.parse("2024-05-11");
        RecordatorioUpdateRequest request = null;


        BaseResponse response = new BaseResponse();
        response.setMessage("Recordatorio actualizado con exito.");
        Mockito.when(service.update(request)).thenThrow(new ApiException("error Message"));
        ResponseEntity<?> responseEntity = controller.patchRecordatorio(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }

    @Test
    void testDeleteRecordatorioSuccess() throws ParseException {

        Integer id= 1;

        BaseResponse response = new BaseResponse();
        response.setMessage("Recordatorio elimnado con exito.");

        Mockito.when(service.delete(id)).thenReturn(response);
        ResponseEntity<?> responseEntity = controller.deleteRecordatorio(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        BaseResponse actualResponse = (BaseResponse) responseEntity.getBody();
        assertEquals(actualResponse.getMessage(), response.getMessage());
    }

    @Test
    void testDeleteRecordatorioThrowException() throws ParseException {

        Integer id= null;



        Mockito.when(service.delete(id)).thenThrow(new ApiException("error Message"));
        ResponseEntity<?> responseEntity = controller.deleteRecordatorio(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }



}

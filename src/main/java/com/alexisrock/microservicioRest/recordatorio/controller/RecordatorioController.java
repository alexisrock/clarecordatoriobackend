package com.alexisrock.microservicioRest.recordatorio.controller;

import com.alexisrock.microservicioRest.Common.BaseResponse;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioRequest;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioResponse;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioService;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioUpdateRequest;
import com.alexisrock.microservicioRest.recordatorio.domain.entities.Recordatorio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recordatorio")
@Tag(name = "Recordatorios", description = "Operaciones sobre recordatorios")
public class RecordatorioController {


    @Autowired
    private RecordatorioService recordatorioService;


    @GetMapping("/getrecordatoriosbyid/{id}")
    @Operation(summary = "Traer los recordatorios por el id del usuario")
    public ResponseEntity<List<RecordatorioResponse>> getRecordatoriosById(@PathVariable Integer id) {
        List<RecordatorioResponse> responseList = recordatorioService.getRecordatoriosById(id);
        return ResponseEntity.ok().body(responseList);
    }


    @PostMapping("/create")
    @Operation(summary = "crear  recordatorios por usuario")
    public ResponseEntity<?> createRecordatorio(@RequestBody RecordatorioRequest request) {
        try {
            BaseResponse response =  recordatorioService.create(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PatchMapping("/patchrecordatorio")
    @Operation(summary = "actualizar los estados del recordatorio del usuario")
    public ResponseEntity<?> patchRecordatorio(@RequestBody RecordatorioUpdateRequest request) {
        try {
            BaseResponse response =  recordatorioService.update(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar el recordatorio del usuario")

    public ResponseEntity<?> deleteRecordatorio(@PathVariable Integer id) {
        try {
            BaseResponse response =  recordatorioService.delete(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


}

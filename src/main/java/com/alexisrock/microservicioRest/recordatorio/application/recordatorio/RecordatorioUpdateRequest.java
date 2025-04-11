package com.alexisrock.microservicioRest.recordatorio.application.recordatorio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;

public class RecordatorioUpdateRequest {


    private Integer Id;
    private String Descripcion;
    private Date FechaRegistro;
    private Time Hora;
    private Boolean CheckRecordatorio;
    private Boolean EsRecurente;
    private Boolean Estado;
    private Integer IdUsuario;

    public RecordatorioUpdateRequest() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time hora) {
        Hora = hora;
    }

    public Boolean isCheckRecordatorio() {
        return CheckRecordatorio;
    }

    public void setCheckRecordatorio(Boolean checkRecordatorio) {
        CheckRecordatorio = checkRecordatorio;
    }

    public Boolean isEsRecurente() {
        return EsRecurente;
    }

    public void setEsRecurente(Boolean esRecurente) {
        EsRecurente = esRecurente;
    }

    public Boolean isEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }
}

package com.alexisrock.microservicioRest.recordatorio.application.recordatorio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;

public class RecordatorioRequest {

    @NotBlank(message = "La descripcion es obligatoria")
    private String Descripcion;
    @NotNull(message = "La fecha es obligatoria")
    private Date FechaRegistro;
    @NotNull(message = "La hora es obligatoria")
    private Time Hora;
    private boolean CheckRecordatorio;
    private boolean EsRecurente;
    private boolean Estado;
    private Integer IdUsuario;


    public RecordatorioRequest(String descripcion, Date fechaRegistro, Time hora, boolean checkRecordatorio, boolean esRecurente, boolean estado, Integer idUsuario) {
        Descripcion = descripcion;
        FechaRegistro = fechaRegistro;
        Hora = hora;
        CheckRecordatorio = checkRecordatorio;
        EsRecurente = esRecurente;
        Estado = estado;
        IdUsuario = idUsuario;
    }
    public RecordatorioRequest(){ }

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

    public boolean isCheckRecordatorio() {
        return CheckRecordatorio;
    }

    public void setCheckRecordatorio(boolean checkRecordatorio) {
        CheckRecordatorio = checkRecordatorio;
    }

    public boolean isEsRecurente() {
        return EsRecurente;
    }

    public void setEsRecurente(boolean esRecurente) {
        EsRecurente = esRecurente;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }
}

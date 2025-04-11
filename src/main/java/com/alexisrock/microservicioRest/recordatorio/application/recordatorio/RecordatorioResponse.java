package com.alexisrock.microservicioRest.recordatorio.application.recordatorio;

import jakarta.persistence.Column;

import java.sql.Time;
import java.util.Date;

public class RecordatorioResponse {

    private Integer Id;
    private String Descripcion;
    private Date FechaRegistro;
    private Time Hora;
    private boolean CheckRecordatorio;
    private boolean EsRecurente;
    private boolean Estado;
    private Integer IdUsuario;

    public RecordatorioResponse(Integer id, String descripcion, Date fechaRegistro, Time hora, boolean checkRecordatorio, boolean esRecurente, boolean estado, Integer idUsuario) {
        Id = id;
        Descripcion = descripcion;
        FechaRegistro = fechaRegistro;
        Hora = hora;
        CheckRecordatorio = checkRecordatorio;
        EsRecurente = esRecurente;
        Estado = estado;
        IdUsuario = idUsuario;
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

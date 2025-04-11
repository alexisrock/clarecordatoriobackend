package com.alexisrock.microservicioRest.recordatorio.domain.entities;


import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table( name = "Recordatorio", schema = "dbo")
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name="Descripcion", length = 4000, nullable = false)
    private String Descripcion;
    @Column(name="Fecharegistro",   nullable = true)
    private Date Fecharegistro;
    @Column(name="Hora",   nullable = true)
    private Time Hora;
    @Column(name="Checkrecordatorio",   nullable = true)
    private boolean Checkrecordatorio;
    @Column(name="Esrecurente",   nullable = true)
    private boolean Esrecurente;
    @Column(name="Estado",   nullable = true)
    private boolean Estado;
    @Column(name="Idusuario",   nullable = true)
    private Integer Idusuario;


    public Recordatorio(String descripcion, Date fecharegistro, Time hora, boolean checkrecordatorio, boolean esrecurente, boolean estado, int idUsuario) {
        Descripcion = descripcion;
        Fecharegistro = fecharegistro;
        Hora = hora;
        Checkrecordatorio = checkrecordatorio;
        Esrecurente = esrecurente;
        Estado = estado;
        Idusuario = idUsuario;
    }

    public Recordatorio(){}

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

    public Date getFecharegistro() {
        return Fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        Fecharegistro = fecharegistro;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time hora) {
        Hora = hora;
    }

    public boolean isCheckrecordatorio() {
        return Checkrecordatorio;
    }

    public void setCheckrecordatorio(boolean checkrecordatorio) {
        Checkrecordatorio = checkrecordatorio;
    }

    public boolean isEsrecurente() {
        return Esrecurente;
    }

    public void setEsrecurente(boolean esrecurente) {
        Esrecurente = esrecurente;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public Integer getIdusuario() {
        return Idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        Idusuario = idusuario;
    }










}

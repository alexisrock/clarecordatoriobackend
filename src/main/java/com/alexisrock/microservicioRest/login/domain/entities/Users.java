package com.alexisrock.microservicioRest.login.domain.entities;


import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Users", schema = "dbo")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name="Nombre", length = 800, nullable = false)
    private String Nombre;
    @Column(name="Email", length = 255, nullable = false)
    private String Email;
    @Column(name="Password", length = 4000, nullable = false)
    private String Password;
    @Column(name="Celular", length = 10, nullable = true)
    private String Celular;
    @Column(name="Fecha_registro",   nullable = true)
    private Date Fecha_registro;
    @Column(name="Estado",   nullable = true)
    private boolean Estado;


    public Users(String nombre, String email, String password, String celular, Date fecha_registro, boolean estado) {
        Nombre = nombre;
        Email = email;
        Password = password;
        Celular = celular;
        Fecha_registro = fecha_registro;
        Estado = estado;
    }


    public Users(){}

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

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

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public Date getFecha_registro() {
        return Fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        Fecha_registro = fecha_registro;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }
}

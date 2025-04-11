package com.alexisrock.microservicioRest.login.domain.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Configuration")
public class Configuration {

    @Id
    private String Id;
    @Column(name="Value", length = 500, nullable = false)
    private String Value;
    @Column(name="Description", length = 2000, nullable = true)

    private String Description;

    public Configuration(String id, String value, String description) {
        Id = id;
        Value = value;
        Description = description;
    }

    public Configuration(){

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

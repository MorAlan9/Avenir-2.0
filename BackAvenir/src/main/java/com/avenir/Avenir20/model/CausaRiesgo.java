package com.avenir.Avenir20.model;

import jakarta.persistence.*;
import org.hibernate.property.access.internal.AbstractSetterMethodSerialForm;

@Entity
@Table(name = "causa_riesgo")
public class CausaRiesgo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private boolean activo;

    public CausaRiesgo(){}

    //GETTERS
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isActivo() {
        return activo;
    }
    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

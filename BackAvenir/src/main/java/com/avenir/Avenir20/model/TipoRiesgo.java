package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_riesgo")
public class TipoRiesgo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private boolean activo;

    public TipoRiesgo(){}

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

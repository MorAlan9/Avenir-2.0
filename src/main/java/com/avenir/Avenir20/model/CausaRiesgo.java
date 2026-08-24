package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "causa_riesgo")
public class CausaRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nombre;

    // 🌟 Agregamos descripcion para solucionar la restricción NOT NULL de la BD
    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private boolean estado = true;

    // Constructores
    public CausaRiesgo() {}

    public CausaRiesgo(String nombre) {
        this.nombre = nombre;
        this.descripcion = nombre; // Evita mandar nulo a PostgreSQL
        this.estado = true;
    }

    public CausaRiesgo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = true;
    }

    public CausaRiesgo(Integer id, String nombre, String descripcion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
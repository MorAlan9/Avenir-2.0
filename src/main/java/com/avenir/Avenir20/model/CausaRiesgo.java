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

    @Column(nullable = false)
    private boolean estado = true;

    public CausaRiesgo() {}

    public CausaRiesgo(String nombre) {
        this.nombre = nombre;
        this.estado = true;
    }

    public CausaRiesgo(Integer id, String nombre, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    // Getters
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean getEstado() { return estado; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEstado(boolean estado) { this.estado = estado; }
}
package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "probabilidad_prioridad")
public class ProbabilidadPrioridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private boolean activo = true;

    public ProbabilidadPrioridad() {}

    public ProbabilidadPrioridad(String nombre) {
        this.nombre = nombre;
        this.activo = true;
    }

    // Getters
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean getActivo() { return activo; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
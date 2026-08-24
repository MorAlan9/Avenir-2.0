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

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Integer nivel = 1;

    // 🌟 Agregamos la columna 'valor' mapeada para PostgreSQL
    @Column(nullable = false)
    private Integer valor = 1;

    @Column(nullable = false)
    private boolean activo = true;

    public ProbabilidadPrioridad() {}

    public ProbabilidadPrioridad(String nombre, Integer nivel) {
        this.nombre = nombre;
        this.descripcion = nombre;
        this.nivel = nivel;
        this.valor = nivel; // Asigna el mismo valor numérico
        this.activo = true;
    }

    @PrePersist
    @PreUpdate
    public void sincronizarDatos() {
        if (this.descripcion == null || this.descripcion.trim().isEmpty()) {
            this.descripcion = this.nombre;
        }
        if (this.valor == null) {
            this.valor = this.nivel != null ? this.nivel : 1;
        }
        if (this.nivel == null) {
            this.nivel = this.valor != null ? this.valor : 1;
        }
    }

    // Getters
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Integer getNivel() { return nivel; }
    public Integer getValor() { return valor; }
    public boolean getActivo() { return activo; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
        this.valor = nivel;
    }
    public void setValor(Integer valor) {
        this.valor = valor;
        if (this.nivel == null) this.nivel = valor;
    }
    public void setActivo(boolean activo) { this.activo = activo; }
}
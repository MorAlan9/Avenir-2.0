package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permisos")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermiso;

    @Column(nullable = false, unique = true)
    private String nombre;

    // 👇 AGREGAMOS ESTE CAMPO CON VALOR TRUE POR DEFECTO
    @Column(nullable = false)
    private Boolean activo = true;

    // Constructors
    public Permiso() {}

    public Permiso(String nombre) {
        this.nombre = nombre;
        this.activo = true; // Se asegura de que no viaje en null
    }

    public Permiso(String nombre, Boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getIdPermiso() { return idPermiso; }
    public void setIdPermiso(Long idPermiso) { this.idPermiso = idPermiso; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
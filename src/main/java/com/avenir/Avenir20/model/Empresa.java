package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String cuit;

    @Column(length = 255)
    private String direccion;

    @Column(nullable = false)
    private Boolean activo;

    // Constructor vacío obligatorio para Spring Boot
    public Empresa() {
        this.activo = true; // Por defecto arranca activa
    }

    public Empresa(String nombre, String cuit, String direccion) {
        this.nombre = nombre;
        this.cuit = cuit;
        this.direccion = direccion;
        this.activo = true;
    }

    // ==============================
    // GETTERS Y SETTERS
    // ==============================

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_persona")
public class TipoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoPersona;

    @Column(nullable = false, unique = true)
    private String nombre;


    // 1. Constructor vacío (OBLIGATORIO para JPA/Hibernate)
    public TipoPersona() {
    }

    // 2. Constructor con parámetros (para crear nuevos roles desde código)
    public TipoPersona(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(Long idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
package com.avenir.Avenir20.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "tipo_persona")
public class TipoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoPersona;

    @Column(nullable = false, unique = true)
    private String nombre;

    // 👇 NUEVO: Relación de muchos a muchos con permisos
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tipo_persona_permisos",
            joinColumns = @JoinColumn(name = "id_tipo_persona"),
            inverseJoinColumns = @JoinColumn(name = "id_permiso")
    )
    private Set<Permiso> permisos = new HashSet<>();

    public TipoPersona() {}

    public TipoPersona(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getIdTipoPersona() { return idTipoPersona; }
    public void setIdTipoPersona(Long idTipoPersona) { this.idTipoPersona = idTipoPersona; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Set<Permiso> getPermisos() { return permisos; }
    public void setPermisos(Set<Permiso> permisos) { this.permisos = permisos; }
}
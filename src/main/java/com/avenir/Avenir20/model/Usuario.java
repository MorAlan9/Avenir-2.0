package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    // Usamos esto para el "Borrado lógico" (UH-9)
    // Por defecto, cuando creámos un usuario, está activo.
    @Column(nullable = false)
    private Boolean activo = true;


    //@ManyToOne
    //@JoinColumn(name = "id_tipo_persona", nullable = false)  //Esta linea se comenta hasta que agreguemos la gestion de permisos
    private String tipoPersona;//Por lo mismo, hasta entonces tipoPersona será String

    // 1. Constructor vacío (Obligatorio para que Hibernate funcione)
    public Usuario() {
    }

    // 2. Constructor con todo (para crear usuarios rápidamente en el código)
    public Usuario(String nombre, String apellido, String email, String contrasena, String tipoPersona) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoPersona = tipoPersona;
        this.activo = true; // Por defecto siempre activo
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
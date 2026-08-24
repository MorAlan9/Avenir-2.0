package com.avenir.Avenir20.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(nullable = false)
    private Boolean activo = false;

    // 📍 Campos de Geolocalización Estructurada
    private String direccion;
    private String pais;
    private String provincia;
    private String ciudad;
    private String barrio;
    private String calle;
    private String numero;
    private Double latitud;
    private Double longitud;

    // 🌟 PLAN A APLICADO: Habilitamos valores nulos para el registro de usuarios estándar
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_tipo_persona", nullable = true)
    private TipoPersona tipoPersona;

    // 🌟 Permisos individuales por usuario (Overrides)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_permiso_especifico",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_permiso")
    )
    private Set<Permiso> permisosEspecificos = new HashSet<>();

    // 1. Constructor vacío
    public Usuario() {
    }

    // 2. Constructor con parámetros principales
    public Usuario(String nombre, String apellido, String email, String contrasena, TipoPersona tipoPersona) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoPersona = tipoPersona;
        this.activo = true;
    }

    // --- GETTERS Y SETTERS ---

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean isActivo() {
        return this.activo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Set<Permiso> getPermisosEspecificos() {
        return permisosEspecificos;
    }

    public void setPermisosEspecificos(Set<Permiso> permisosEspecificos) {
        this.permisosEspecificos = permisosEspecificos;
    }
}
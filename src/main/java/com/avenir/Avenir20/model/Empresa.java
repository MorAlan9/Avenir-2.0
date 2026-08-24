package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(unique = true, nullable = false)
    private String cuit;

    private String nombre;
    private String direccion;
    private Boolean activo;

    // 📍 Campos de Geolocalización Estructurada
    private String pais;
    private String provincia;
    private String ciudad;
    private String barrio;
    private String calle;
    private String numero;
    private Double latitud;
    private Double longitud;

    public Empresa() {}

    public Long getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getCuit() { return cuit; }
    public void setCuit(String cuit) { this.cuit = cuit; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getBarrio() { return barrio; }
    public void setBarrio(String barrio) { this.barrio = barrio; }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }
}
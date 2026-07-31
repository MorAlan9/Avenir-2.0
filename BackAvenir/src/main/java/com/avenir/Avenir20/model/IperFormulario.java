package com.avenir.Avenir20.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "iperFormulario")
public class IperFormulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idresponsable;//Esta variable sería para facilitar el envío a la base de datos.
    private LocalDate fecha;
    private String turno;
    private String empresa;
    private String estado;
    private String tipoRiesgo;
    private String descripcionRiesgo;
    private String causaRiesgo;
    private String sector;
    private String categoriaRiesgo;
    private String nivelRiesgo;
    private Boolean existenMedidas;
    private String descripcionMedidas;
    private String impactoPotencialRiesgo;
    private String probabilidadOcurrencia;
    private String prioridadRiesgo;
    private String accionesSugeridas;
    private String responsableDeAcciones;
    private LocalDate fechaAlternativaImplementacion;
    private String riesgoEliminado;
    private String impactoResidual;
    private String comentario;
    private LocalDate fechaCierre;
    private String nombreArchivo;
    private byte[] contenidoArchivo;

    public IperFormulario(){}

    //GETTERS

    public int getId() {
        return id;
    }

    public int getIdresponsable() {
        return idresponsable;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTurno() {
        return turno;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipoRiesgo() {
        return tipoRiesgo;
    }

    public String getDescripcionRiesgo() {
        return descripcionRiesgo;
    }

    public String getCausaRiesgo() {
        return causaRiesgo;
    }

    public String getSector() {
        return sector;
    }

    public String getCategoriaRiesgo() {
        return categoriaRiesgo;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public Boolean getExistenMedidas() {
        return existenMedidas;
    }

    public String getDescripcionMedidas() {
        return descripcionMedidas;
    }

    public String getImpactoPotencialRiesgo() {
        return impactoPotencialRiesgo;
    }

    public String getProbabilidadOcurrencia() {
        return probabilidadOcurrencia;
    }

    public String getPrioridadRiesgo() {
        return prioridadRiesgo;
    }

    public String getAccionesSugeridas() {
        return accionesSugeridas;
    }

    public String getResponsableDeAcciones() {
        return responsableDeAcciones;
    }

    public LocalDate getFechaAlternativaImplementacion() {
        return fechaAlternativaImplementacion;
    }

    public String getRiesgoEliminado() {
        return riesgoEliminado;
    }

    public String getImpactoResidual() {
        return impactoResidual;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public byte[] getContenidoArchivo() {
        return contenidoArchivo;
    }

    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setIdresponsable(int idresponsable) {
        this.idresponsable = idresponsable;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTipoRiesgo(String tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public void setDescripcionRiesgo(String descripcionRiesgo) {
        this.descripcionRiesgo = descripcionRiesgo;
    }

    public void setCausaRiesgo(String causaRiesgo) {
        this.causaRiesgo = causaRiesgo;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setCategoriaRiesgo(String categoriaRiesgo) {
        this.categoriaRiesgo = categoriaRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public void setExistenMedidas(Boolean existenMedidas) {
        this.existenMedidas = existenMedidas;
    }

    public void setDescripcionMedidas(String descripcionMedidas) {
        this.descripcionMedidas = descripcionMedidas;
    }

    public void setImpactoPotencialRiesgo(String impactoPotencialRiesgo) {
        this.impactoPotencialRiesgo = impactoPotencialRiesgo;
    }

    public void setProbabilidadOcurrencia(String probabilidadOcurrencia) {
        this.probabilidadOcurrencia = probabilidadOcurrencia;
    }

    public void setPrioridadRiesgo(String prioridadRiesgo) {
        this.prioridadRiesgo = prioridadRiesgo;
    }

    public void setAccionesSugeridas(String accionesSugeridas) {
        this.accionesSugeridas = accionesSugeridas;
    }

    public void setResponsableDeAcciones(String responsableDeAcciones) {
        this.responsableDeAcciones = responsableDeAcciones;
    }

    public void setFechaAlternativaImplementacion(LocalDate fechaAlternativaImplementacion) {
        this.fechaAlternativaImplementacion = fechaAlternativaImplementacion;
    }

    public void setRiesgoEliminado(String riesgoEliminado) {
        this.riesgoEliminado = riesgoEliminado;
    }

    public void setImpactoResidual(String impactoResidual) {
        this.impactoResidual = impactoResidual;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void setContenidoArchivo(byte[] contenidoArchivo) {
        this.contenidoArchivo = contenidoArchivo;
    }
}

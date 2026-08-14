package com.avenir.Avenir20.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "iper_formulario")
public class IPERFormulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_responsable")
    private Integer idresponsable;

    private LocalDate fecha;

    @Column(length = 50)
    private String turno;

    @Column(length = 100)
    private String empresa;

    @Column(length = 50)
    private String estado;

    @Column(name = "tipo_riesgo", length = 100)
    private String tipoRiesgo;

    @Column(name = "descripcion_riesgo", columnDefinition = "TEXT")
    private String descripcionRiesgo;

    @Column(name = "causa_riesgo", length = 150)
    private String causaRiesgo;

    @Column(length = 100)
    private String sector;

    @Column(name = "categoria_riesgo", length = 100)
    private String categoriaRiesgo;

    @Column(name = "nivel_riesgo", length = 50)
    private String nivelRiesgo;

    @Column(name = "existen_medidas")
    private Boolean existenMedidas;

    @Column(name = "descripcion_medidas", columnDefinition = "TEXT")
    private String descripcionMedidas;

    @Column(name = "impacto_potencial_riesgo", columnDefinition = "TEXT")
    private String impactoPotencialRiesgo;

    @Column(name = "probabilidad_ocurrencia", length = 50)
    private String probabilidadOcurrencia;

    @Column(name = "prioridad_riesgo", length = 50)
    private String prioridadRiesgo;

    @Column(name = "acciones_sugeridas", columnDefinition = "TEXT")
    private String accionesSugeridas;

    @Column(name = "responsable_de_acciones", length = 100)
    private String responsableDeAcciones;

    @Column(name = "fecha_alternativa_implementacion")
    private LocalDate fechaAlternativaImplementacion;

    @Column(name = "riesgo_eliminado", length = 10)
    private String riesgoEliminado;

    @Column(name = "impacto_residual", columnDefinition = "TEXT")
    private String impactoResidual;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Lob
    @Column(name = "contenido_archivo", columnDefinition = "BYTEA")
    private byte[] contenidoArchivo;

    public IPERFormulario() {}

    // Getters
    public Integer getId() { return id; }
    public Integer getIdresponsable() { return idresponsable; }
    public LocalDate getFecha() { return fecha; }
    public String getTurno() { return turno; }
    public String getEmpresa() { return empresa; }
    public String getEstado() { return estado; }
    public String getTipoRiesgo() { return tipoRiesgo; }
    public String getDescripcionRiesgo() { return descripcionRiesgo; }
    public String getCausaRiesgo() { return causaRiesgo; }
    public String getSector() { return sector; }
    public String getCategoriaRiesgo() { return categoriaRiesgo; }
    public String getNivelRiesgo() { return nivelRiesgo; }
    public Boolean getExistenMedidas() { return existenMedidas; }
    public String getDescripcionMedidas() { return descripcionMedidas; }
    public String getImpactoPotencialRiesgo() { return impactoPotencialRiesgo; }
    public String getProbabilidadOcurrencia() { return probabilidadOcurrencia; }
    public String getPrioridadRiesgo() { return prioridadRiesgo; }
    public String getAccionesSugeridas() { return accionesSugeridas; }
    public String getResponsableDeAcciones() { return responsableDeAcciones; }
    public LocalDate getFechaAlternativaImplementacion() { return fechaAlternativaImplementacion; }
    public String getRiesgoEliminado() { return riesgoEliminado; }
    public String getImpactoResidual() { return impactoResidual; }
    public String getComentario() { return comentario; }
    public LocalDate getFechaCierre() { return fechaCierre; }
    public String getNombreArchivo() { return nombreArchivo; }
    public byte[] getContenidoArchivo() { return contenidoArchivo; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setIdresponsable(Integer idresponsable) { this.idresponsable = idresponsable; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTurno(String turno) { this.turno = turno; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setTipoRiesgo(String tipoRiesgo) { this.tipoRiesgo = tipoRiesgo; }
    public void setDescripcionRiesgo(String descripcionRiesgo) { this.descripcionRiesgo = descripcionRiesgo; }
    public void setCausaRiesgo(String causaRiesgo) { this.causaRiesgo = causaRiesgo; }
    public void setSector(String sector) { this.sector = sector; }
    public void setCategoriaRiesgo(String categoriaRiesgo) { this.categoriaRiesgo = categoriaRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }
    public void setExistenMedidas(Boolean existenMedidas) { this.existenMedidas = existenMedidas; }
    public void setDescripcionMedidas(String descripcionMedidas) { this.descripcionMedidas = descripcionMedidas; }
    public void setImpactoPotencialRiesgo(String impactoPotencialRiesgo) { this.impactoPotencialRiesgo = impactoPotencialRiesgo; }
    public void setProbabilidadOcurrencia(String probabilidadOcurrencia) { this.probabilidadOcurrencia = probabilidadOcurrencia; }
    public void setPrioridadRiesgo(String prioridadRiesgo) { this.prioridadRiesgo = prioridadRiesgo; }
    public void setAccionesSugeridas(String accionesSugeridas) { this.accionesSugeridas = accionesSugeridas; }
    public void setResponsableDeAcciones(String responsableDeAcciones) { this.responsableDeAcciones = responsableDeAcciones; }
    public void setFechaAlternativaImplementacion(LocalDate fechaAlternativaImplementacion) { this.fechaAlternativaImplementacion = fechaAlternativaImplementacion; }
    public void setRiesgoEliminado(String riesgoEliminado) { this.riesgoEliminado = riesgoEliminado; }
    public void setImpactoResidual(String impactoResidual) { this.impactoResidual = impactoResidual; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public void setFechaCierre(LocalDate fechaCierre) { this.fechaCierre = fechaCierre; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }
    public void setContenidoArchivo(byte[] contenidoArchivo) { this.contenidoArchivo = contenidoArchivo; }
}
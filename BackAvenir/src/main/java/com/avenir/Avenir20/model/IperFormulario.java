package com.avenir.Avenir20.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "iperFormulario")
public class IperFormulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    private Usuario responsable;//Esta variable sería para facilitar el envío a la base de datos.
    private LocalDate fecha;
    private String turno;
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;
    @ManyToOne
    @JoinColumn(name = "tipoRiesgo_id", nullable = false)
    private TipoRiesgo tipoRiesgo;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcionRiesgo;
    @ManyToOne
    @JoinColumn(name = "causaRiesgo_id", nullable = false)
    private CausaRiesgo causaRiesgo;
    private String sector;
    @ManyToOne
    @JoinColumn(name = "categoriaRiesgo_id", nullable = false)
    private CategoriaRiesgo categoriaRiesgo;
    @ManyToOne
    @JoinColumn(name = "nivelRiesgo_id", nullable = false)
    private Severidad nivelRiesgo;
    private Boolean existenMedidas;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcionMedidas;
    @ManyToOne
    @JoinColumn(name = "impactoPotencialRiesgo_id", nullable = false)
    private Severidad impactoPotencialRiesgo;
    private ProbabilidadPrioridad probabilidadOcurrencia;
    private ProbabilidadPrioridad prioridadRiesgo;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String accionesSugeridas;
    private String responsableDeAcciones;
    private LocalDate fechaAlternativaImplementacion;
    private String riesgoEliminado;
    @ManyToOne
    @JoinColumn(name = "impactoResidual_id", nullable = false)
    private ProbabilidadPrioridad impactoResidual;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String comentario;
    private LocalDate fechaCierre;
    private String nombreArchivo;
    private byte[] contenidoArchivo;

    public IperFormulario(){}

    //GETTERS

    public Long getId() {
        return id;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTurno() {
        return turno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Estado getEstado() {
        return estado;
    }

    public TipoRiesgo getTipoRiesgo() {
        return tipoRiesgo;
    }

    public String getDescripcionRiesgo() {
        return descripcionRiesgo;
    }

    public CausaRiesgo getCausaRiesgo() {
        return causaRiesgo;
    }

    public String getSector() {
        return sector;
    }

    public CategoriaRiesgo getCategoriaRiesgo() {
        return categoriaRiesgo;
    }

    public Severidad getNivelRiesgo() {
        return nivelRiesgo;
    }

    public Boolean getExistenMedidas() {
        return existenMedidas;
    }

    public String getDescripcionMedidas() {
        return descripcionMedidas;
    }

    public Severidad getImpactoPotencialRiesgo() {
        return impactoPotencialRiesgo;
    }

    public ProbabilidadPrioridad getProbabilidadOcurrencia() {
        return probabilidadOcurrencia;
    }

    public ProbabilidadPrioridad getPrioridadRiesgo() {
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

    public ProbabilidadPrioridad getImpactoResidual() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setTipoRiesgo(TipoRiesgo tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public void setDescripcionRiesgo(String descripcionRiesgo) {
        this.descripcionRiesgo = descripcionRiesgo;
    }

    public void setCausaRiesgo(CausaRiesgo causaRiesgo) {
        this.causaRiesgo = causaRiesgo;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setCategoriaRiesgo(CategoriaRiesgo categoriaRiesgo) {
        this.categoriaRiesgo = categoriaRiesgo;
    }

    public void setNivelRiesgo(Severidad nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public void setExistenMedidas(Boolean existenMedidas) {
        this.existenMedidas = existenMedidas;
    }

    public void setDescripcionMedidas(String descripcionMedidas) {
        this.descripcionMedidas = descripcionMedidas;
    }

    public void setImpactoPotencialRiesgo(Severidad impactoPotencialRiesgo) {
        this.impactoPotencialRiesgo = impactoPotencialRiesgo;
    }

    public void setProbabilidadOcurrencia(ProbabilidadPrioridad probabilidadOcurrencia) {
        this.probabilidadOcurrencia = probabilidadOcurrencia;
    }

    public void setPrioridadRiesgo(ProbabilidadPrioridad prioridadRiesgo) {
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

    public void setImpactoResidual(ProbabilidadPrioridad impactoResidual) {
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

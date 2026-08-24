package com.avenir.Avenir20.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ats_registros")
public class Ats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAts;

    private Long empresaId;
    private String usuarioAuditorEmail;
    private LocalDate fechaRealizacion;
    private String ubicacionSector;
    private String tareaARealizar;

    private Long tipoRiesgoId;
    private Long categoriaRiesgoId;
    private Long causaRiesgoId;
    private Long probabilidadId;

    private String estado; // PENDIENTE, APROBADO, RECHAZADO

    @OneToMany(mappedBy = "ats", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtsPaso> pasosTarea = new ArrayList<>();

    public Ats() {}

    public void addPaso(AtsPaso paso) {
        pasosTarea.add(paso);
        paso.setAts(this);
    }

    public Long getIdAts() { return idAts; }
    public void setIdAts(Long idAts) { this.idAts = idAts; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }

    public String getUsuarioAuditorEmail() { return usuarioAuditorEmail; }
    public void setUsuarioAuditorEmail(String usuarioAuditorEmail) { this.usuarioAuditorEmail = usuarioAuditorEmail; }

    public LocalDate getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(LocalDate fechaRealizacion) { this.fechaRealizacion = fechaRealizacion; }

    public String getUbicacionSector() { return ubicacionSector; }
    public void setUbicacionSector(String ubicacionSector) { this.ubicacionSector = ubicacionSector; }

    public String getTareaARealizar() { return tareaARealizar; }
    public void setTareaARealizar(String tareaARealizar) { this.tareaARealizar = tareaARealizar; }

    public Long getTipoRiesgoId() { return tipoRiesgoId; }
    public void setTipoRiesgoId(Long tipoRiesgoId) { this.tipoRiesgoId = tipoRiesgoId; }

    public Long getCategoriaRiesgoId() { return categoriaRiesgoId; }
    public void setCategoriaRiesgoId(Long categoriaRiesgoId) { this.categoriaRiesgoId = categoriaRiesgoId; }

    public Long getCausaRiesgoId() { return causaRiesgoId; }
    public void setCausaRiesgoId(Long causaRiesgoId) { this.causaRiesgoId = causaRiesgoId; }

    public Long getProbabilidadId() { return probabilidadId; }
    public void setProbabilidadId(Long probabilidadId) { this.probabilidadId = probabilidadId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<AtsPaso> getPasosTarea() { return pasosTarea; }
    public void setPasosTarea(List<AtsPaso> pasosTarea) { this.pasosTarea = pasosTarea; }
}
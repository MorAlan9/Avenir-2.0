package com.avenir.Avenir20.dto;

import java.time.LocalDate;
import java.util.List;

public class AtsDTO {
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
    private String estado;
    private List<AtsPasoDTO> pasosTarea;

    public static class AtsPasoDTO {
        private Integer paso;
        private String descripcion;
        private String peligro;
        private String riesgo;
        private String medidaControl;

        public Integer getPaso() { return paso; }
        public void setPaso(Integer paso) { this.paso = paso; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public String getPeligro() { return peligro; }
        public void setPeligro(String peligro) { this.peligro = peligro; }
        public String getRiesgo() { return riesgo; }
        public void setRiesgo(String riesgo) { this.riesgo = riesgo; }
        public String getMedidaControl() { return medidaControl; }
        public void setMedidaControl(String medidaControl) { this.medidaControl = medidaControl; }
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
    public List<AtsPasoDTO> getPasosTarea() { return pasosTarea; }
    public void setPasosTarea(List<AtsPasoDTO> pasosTarea) { this.pasosTarea = pasosTarea; }
}
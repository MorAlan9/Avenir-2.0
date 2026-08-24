package com.avenir.Avenir20.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ats_pasos")
public class AtsPaso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaso;

    private Integer paso;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 500)
    private String peligro;

    @Column(length = 500)
    private String riesgo;

    @Column(length = 500)
    private String medidaControl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ats_id")
    private Ats ats;

    public AtsPaso() {}

    public Long getIdPaso() { return idPaso; }
    public void setIdPaso(Long idPaso) { this.idPaso = idPaso; }

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

    public Ats getAts() { return ats; }
    public void setAts(Ats ats) { this.ats = ats; }
}
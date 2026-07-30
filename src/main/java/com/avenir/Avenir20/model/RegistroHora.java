package com.avenir.Avenir20.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registro_horas")
public class RegistroHora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private LocalDate fecha;
    private Double horasDedicadas;

    @Column(length = 500)
    private String tareasRealizadas;

    // 🌟 CAMPO ESTADO (PENDIENTE, APROBADO, RECHAZADO)
    @Column(name = "estado")
    private String estado = "PENDIENTE";

    public RegistroHora() {}

    // Getters
    public Long getIdRegistro() { return idRegistro; }
    public Empresa getEmpresa() { return empresa; }
    public Usuario getUsuario() { return usuario; }
    public LocalDate getFecha() { return fecha; }
    public Double getHorasDedicadas() { return horasDedicadas; }
    public String getTareasRealizadas() { return tareasRealizadas; }
    public String getEstado() { return estado; }

    // Setters
    public void setIdRegistro(Long idRegistro) { this.idRegistro = idRegistro; }
    public void setEmpresa(Empresa e) { this.empresa = e; }
    public void setUsuario(Usuario u) { this.usuario = u; }
    public void setFecha(LocalDate f) { this.fecha = f; }
    public void setHorasDedicadas(Double h) { this.horasDedicadas = h; }
    public void setTareasRealizadas(String t) { this.tareasRealizadas = t; }
    public void setEstado(String estado) { this.estado = estado; }
}
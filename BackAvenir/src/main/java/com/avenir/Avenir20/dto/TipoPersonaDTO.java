package com.avenir.Avenir20.dto;

import java.util.List;

public class TipoPersonaDTO {
    private String nombre;
    private List<Long> permisosIds;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Long> getPermisosIds() { return permisosIds; }
    public void setPermisosIds(List<Long> permisosIds) { this.permisosIds = permisosIds; }
}
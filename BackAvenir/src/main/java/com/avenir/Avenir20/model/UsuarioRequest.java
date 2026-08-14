package com.avenir.Avenir20.model;

public class UsuarioRequest {

    private Usuario usuario;
    private String claveAcceso;

    public UsuarioRequest(){

    }

    public UsuarioRequest(Usuario usuario, String claveAcceso) {
        this.usuario = usuario;
        this.claveAcceso = claveAcceso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }
}

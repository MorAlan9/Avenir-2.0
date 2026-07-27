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
    }
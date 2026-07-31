package com.avenir.Avenir20.config;

import com.avenir.Avenir20.model.Permiso;
import com.avenir.Avenir20.repository.PermisoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PermisoRepository permisoRepository;

    public DataInitializer(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }

    @Override
    public void run(String... args) {
        // 🌟 Lista completa de permisos requeridos por el sistema
        List<String> nombresPermisos = List.of(
                "VER_USUARIOS", "CREAR_USUARIOS", "EDITAR_USUARIOS", "ELIMINAR_USUARIOS",
                "VER_ROLES", "CREAR_ROLES", "EDITAR_ROLES", "ELIMINAR_ROLES",
                "VER_EMPRESAS", "CREAR_EMPRESAS", "EDITAR_EMPRESAS", "ELIMINAR_EMPRESAS",
                "VER_HORARIOS", "REGISTRAR_HORARIOS", "APROBAR_HORARIOS" // 👈 Aseguramos APROBAR_HORARIOS
        );

        // 🚀 Verificamos uno por uno para agregar únicamente los faltantes sin duplicar
        for (String nombre : nombresPermisos) {
            boolean existe = permisoRepository.existsByNombre(nombre);
            if (!existe) {
                permisoRepository.save(new Permiso(nombre));
                System.out.println("✅ Permiso creado automáticamente en PostgreSQL: " + nombre);
            }
        }
    }
}
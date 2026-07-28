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
        // Si no hay permisos en la BD, creamos los iniciales
        if (permisoRepository.count() == 0) {
            List<String> nombresPermisos = List.of(
                    "VER_USUARIOS", "CREAR_USUARIOS", "EDITAR_USUARIOS", "ELIMINAR_USUARIOS",
                    "VER_EMPRESAS", "CREAR_EMPRESAS", "EDITAR_EMPRESAS", "ELIMINAR_EMPRESAS",
                    "VER_HORARIOS", "REGISTRAR_HORARIOS"
            );

            for (String nombre : nombresPermisos) {
                permisoRepository.save(new Permiso(nombre));
            }
            System.out.println("✅ Permisos iniciales cargados con éxito en la Base de Datos.");
        }
    }
}
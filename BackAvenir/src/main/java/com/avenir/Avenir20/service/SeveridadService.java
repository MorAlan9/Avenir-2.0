package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.Severidad;
import com.avenir.Avenir20.repository.SeveridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeveridadService {

    @Autowired
    private SeveridadRepository repository;

    // US: Registrar severidad (y modificarla)
    public Severidad guardar(Severidad severidad) {
        // Validación: Buscar si el nombre ya existe en la base de datos
        Optional<Severidad> existente = repository.findByNombre(severidad.getNombre());

        // Si el nombre existe Y NO es el mismo registro que estamos intentando editar
        if (existente.isPresent() && !existente.get().getId().equals(severidad.getId())) {
            throw new IllegalArgumentException("Ya existe una severidad con este nombre.");
        }

        if (severidad.getId() == null) {
            severidad.setActivo(true); // Si es nuevo, arranca activo por defecto
        }
        return repository.save(severidad);
    }

    // US: Leer todas las severidades
    public List<Severidad> listarTodas() {
        return repository.findAll();
    }

    // US: Buscar severidad por ID
    public Optional<Severidad> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // US: Dar de baja severidad (borrado lógico)
    public Severidad darDeBaja(Long id) {
        Optional<Severidad> severidadOpt = repository.findById(id);
        if (severidadOpt.isPresent()) {
            Severidad severidad = severidadOpt.get();
            severidad.setActivo(false);
            return repository.save(severidad);
        }
        return null;
    }
}

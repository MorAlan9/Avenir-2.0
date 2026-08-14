package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.ProbabilidadPrioridad;
import com.avenir.Avenir20.repository.ProbabilidadPrioridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProbabilidadPrioridadService {
    @Autowired
    private ProbabilidadPrioridadRepository repository;

    // US: Registrar probabilidad/prioridad (y modificarla)
    public ProbabilidadPrioridad guardar(ProbabilidadPrioridad probabilidadPrioridad) {
        // Validación: Buscar si el nombre ya existe en la base de datos
        Optional<ProbabilidadPrioridad> existente = repository.findByNombre(probabilidadPrioridad.getNombre());

        // Si el nombre existe Y NO es el mismo registro que estamos intentando editar
        if (existente.isPresent() && !existente.get().getId().equals(probabilidadPrioridad.getId())) {
            throw new IllegalArgumentException("Ya existe una probabilidad/prioridad con este nombre.");
        }

        if (probabilidadPrioridad.getId() == null) {
            probabilidadPrioridad.setActivo(true); // Si es nuevo, arranca activo por defecto
        }
        return repository.save(probabilidadPrioridad);
    }

    // US: Leer todos los registros
    public List<ProbabilidadPrioridad> listarTodos() {
        return repository.findAll();
    }

    // US: Buscar por ID
    public Optional<ProbabilidadPrioridad> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // US: Dar de baja (borrado lógico)
    public ProbabilidadPrioridad darDeBaja(Long id) {
        Optional<ProbabilidadPrioridad> opt = repository.findById(id);
        if (opt.isPresent()) {
            ProbabilidadPrioridad probabilidadPrioridad = opt.get();
            probabilidadPrioridad.setActivo(false);
            return repository.save(probabilidadPrioridad);
        }
        return null;
    }
}

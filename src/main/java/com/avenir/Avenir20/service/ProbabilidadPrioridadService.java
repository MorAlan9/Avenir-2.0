package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.ProbabilidadPrioridad;
import com.avenir.Avenir20.repository.ProbabilidadPrioridadRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProbabilidadPrioridadService {

    private final ProbabilidadPrioridadRepository repository;

    public ProbabilidadPrioridadService(ProbabilidadPrioridadRepository repository) {
        this.repository = repository;
    }

    public List<ProbabilidadPrioridad> listarTodos() {
        return repository.findAll();
    }

    public List<ProbabilidadPrioridad> listarActivos() {
        return repository.findByActivoTrue();
    }

    public Optional<ProbabilidadPrioridad> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public ProbabilidadPrioridad guardar(ProbabilidadPrioridad item) {
        item.setActivo(true);
        return repository.save(item);
    }

    public ProbabilidadPrioridad actualizar(Integer id, ProbabilidadPrioridad datos) {
        return repository.findById(id).map(prob -> {
            prob.setNombre(datos.getNombre());
            prob.setActivo(datos.getActivo());
            return repository.save(prob);
        }).orElseThrow(() -> new RuntimeException("Probabilidad/Prioridad no encontrada con ID: " + id));
    }

    public void darDeBaja(Integer id) {
        repository.findById(id).ifPresent(prob -> {
            prob.setActivo(false);
            repository.save(prob);
        });
    }
}
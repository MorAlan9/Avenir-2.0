package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.CausaRiesgo;
import com.avenir.Avenir20.repository.CausaRiesgoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CausaRiesgoService {

    private final CausaRiesgoRepository repository;

    public CausaRiesgoService(CausaRiesgoRepository repository) {
        this.repository = repository;
    }

    public List<CausaRiesgo> listarTodos() {
        return repository.findAll();
    }

    public List<CausaRiesgo> listarActivos() {
        return repository.findByEstadoTrue();
    }

    public Optional<CausaRiesgo> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public CausaRiesgo guardar(CausaRiesgo causa) {
        causa.setEstado(true);
        return repository.save(causa);
    }

    public CausaRiesgo actualizar(Integer id, CausaRiesgo datos) {
        return repository.findById(id).map(causa -> {
            causa.setNombre(datos.getNombre());
            causa.setEstado(datos.getEstado());
            return repository.save(causa);
        }).orElseThrow(() -> new RuntimeException("Causa de riesgo no encontrada con ID: " + id));
    }

    public void darDeBaja(Integer id) {
        repository.findById(id).ifPresent(causa -> {
            causa.setEstado(false);
            repository.save(causa);
        });
    }
}
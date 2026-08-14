package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.TipoRiesgo;
import com.avenir.Avenir20.repository.TipoRiesgoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoRiesgoService {

    private final TipoRiesgoRepository repository;

    public TipoRiesgoService(TipoRiesgoRepository repository) {
        this.repository = repository;
    }

    public List<TipoRiesgo> listarTodos() {
        return repository.findAll();
    }

    public List<TipoRiesgo> listarActivos() {
        return repository.findByActivoTrue();
    }

    public Optional<TipoRiesgo> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public TipoRiesgo guardar(TipoRiesgo tipo) {
        tipo.setActivo(true);
        return repository.save(tipo);
    }

    public TipoRiesgo actualizar(Integer id, TipoRiesgo datos) {
        return repository.findById(id).map(tipo -> {
            tipo.setNombre(datos.getNombre());
            tipo.setActivo(datos.getActivo());
            return repository.save(tipo);
        }).orElseThrow(() -> new RuntimeException("Tipo de Riesgo no encontrado con ID: " + id));
    }

    public void darDeBaja(Integer id) {
        repository.findById(id).ifPresent(tipo -> {
            tipo.setActivo(false);
            repository.save(tipo);
        });
    }
}
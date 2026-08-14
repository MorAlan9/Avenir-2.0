package com.avenir.Avenir20.service;

import com.avenir.Avenir20.model.CategoriaRiesgo;
import com.avenir.Avenir20.repository.CategoriaRiesgoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaRiesgoService {

    private final CategoriaRiesgoRepository repository;

    public CategoriaRiesgoService(CategoriaRiesgoRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaRiesgo> listarTodos() {
        return repository.findAll();
    }

    public List<CategoriaRiesgo> listarActivos() {
        return repository.findByActivoTrue();
    }

    public Optional<CategoriaRiesgo> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public CategoriaRiesgo guardar(CategoriaRiesgo categoria) {
        categoria.setActivo(true);
        return repository.save(categoria);
    }

    public CategoriaRiesgo actualizar(Integer id, CategoriaRiesgo datos) {
        return repository.findById(id).map(cat -> {
            cat.setNombre(datos.getNombre());
            cat.setActivo(datos.getActivo());
            return repository.save(cat);
        }).orElseThrow(() -> new RuntimeException("Categoría de riesgo no encontrada con ID: " + id));
    }

    public void darDeBaja(Integer id) {
        repository.findById(id).ifPresent(cat -> {
            cat.setActivo(false);
            repository.save(cat);
        });
    }
}